package com.hcp.mp.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.hcp.common.core.constant.HttpStatus;
import com.hcp.common.core.domain.R;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.core.utils.ip.IpUtils;
import com.hcp.common.redis.service.RedisService;
import com.hcp.mp.constant.MpConstant;
import com.hcp.mp.service.IMiniappService;
import com.hcp.system.api.RemoteMemberBalanceService;
import com.hcp.system.api.domain.MenberBalance;
import com.hcp.system.api.domain.Miniapp;
import com.hcp.mp.ro.BindPhoneRo;
import com.hcp.mp.ro.UserInfoRo;
import com.hcp.mp.service.MpService;
import com.hcp.mp.vo.UserVo;
import com.hcp.system.api.RemoteMemberService;
import com.hcp.system.api.domain.Member;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MpServiceImpl implements MpService {
    @Autowired
    private IMiniappService miniappService;
    @Autowired
    private RemoteMemberService memberService;
    @Autowired
    private RedisService redisService;
    @Resource
    private RemoteMemberBalanceService remoteMemberBalanceService;

    @Override
    public R loginByCode(String appid, String code) {
        try {
            final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            WxMaService wxMaService = getWxMaService(appid);
            if (null == wxMaService) {
                return R.fail("小程序尚未初始化");
            }
            if (!wxMaService.switchover(appid)) {
                return R.fail("APPID无效");
            }
            Miniapp miniapp = miniappService.getById(appid);
            if (null == miniapp) {
                return R.fail("小程序尚未注册");
            }
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            String unionId = session.getUnionid();
            String openId = session.getOpenid();
            if (TextUtils.isBlank(unionId)) {
                return R.fail(HttpStatus.UNAUTHORIZED, "unionId获取失败");
            }
            Member member = memberService.getMemberInfoByOpenId(openId).getData();
            if (member == null) {
                Member memberEntity = new Member();
                memberEntity.setTenantId(miniapp.getTenantId());
                memberEntity.setWeixinOpenid(openId);
                if (memberService.addMemberInfo(memberEntity).getCode() != 200) {
                    return R.fail("用户注册失败");
                }
                //补充默认值
                memberEntity = memberService.getMemberInfoByOpenId(openId).getData();

                MenberBalance menberBalance = new MenberBalance();
                menberBalance.setMemberId(memberEntity.getMemberId());
                menberBalance.setAmount(BigDecimal.ZERO);
                menberBalance.setCreateTime(new Date());
                remoteMemberBalanceService.insertMemberBalance(menberBalance);

            } else {
                 memberService.updateMemberById(member);
            }
            String token = session.getSessionKey();
            assert member != null;
            redisService.setCacheObject(MpConstant.USER_TOKEN + token, member.getMemberId(), 360L, TimeUnit.DAYS);
            UserVo userVo = new UserVo();
            userVo.setToken(token);
            userVo.setMember(member);
            return R.ok(userVo);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return R.fail("登录失败");
        }
    }

    @Override
    public R phoneInfo(String appid, String token, BindPhoneRo bindPhoneRo) {
        return null;
    }

    @Override
    public R update(Long uid, UserInfoRo userInfoRo) {
        return null;
    }

    @Override
    public R userInfo(Long uid) {
        return null;
    }

    public WxMaService getWxMaService(String appId) {
        Miniapp miniapp = miniappService.getById(appId);
        WxMaService maService = new WxMaServiceImpl();
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(miniapp.getAppId());
        config.setSecret(miniapp.getAppSecret());
        maService.setWxMaConfig(config);
        return maService;
    }


    @Override
    public R appletBindMobile(String openId, String code, String appId) {

        WxMaService wxMaService = getWxMaService(appId);
        if (null == wxMaService) {
            return R.fail("小程序尚未初始化");
        }
        if (!wxMaService.switchover(appId)) {
            return R.fail("APPID无效");
        }
        Miniapp miniapp = miniappService.getById(appId);
        if (null == miniapp) {
            return R.fail("小程序尚未注册");

        }

        WxMaPhoneNumberInfo phoneNoInfo = null;
        try {
            phoneNoInfo = wxMaService.getUserService().getPhoneNoInfo(code);
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }
        log.info("用户:{},phoneNoInfo:{}", openId, JSONUtil.toJsonStr(phoneNoInfo));
        if (ObjectUtil.isNotEmpty(phoneNoInfo)) {
            String phoneNumber = phoneNoInfo.getPhoneNumber();
            Member member = memberService.getMemberInfoByOpenId(openId).getData();

            member.setMobile(phoneNumber);
            memberService.updateMemberById(member);
            MenberBalance menberBalance = remoteMemberBalanceService.getMenberBalanceByUserId(member.getMemberId()).getData();

            if (ObjectUtil.isNotEmpty(menberBalance)) {
                menberBalance.setMobile(phoneNumber);
                remoteMemberBalanceService.updateMemberBalance(menberBalance);
            }

            return R.ok(member);
        }
        return R.fail("手机号绑定失败!");
    }
}