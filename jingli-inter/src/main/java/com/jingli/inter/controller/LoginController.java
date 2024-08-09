package com.jingli.inter.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSONObject;
import com.jingli.inter.domain.ApiResult;
import com.jingli.inter.entity.TUser;
import com.jingli.inter.service.TUserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/outer/login")
@Slf4j
public class LoginController {
    @Autowired
    private WxMaService wxMaService;
    @Autowired
    private TUserService userService;

    @GetMapping("/appletWeChatLogin")
    public ApiResult<TUser> appletWeChatLogin(@RequestParam("code") String code,
                                              @RequestParam("encryptedData") String encryptedData,
                                              @RequestParam("iv") String iv) {
        try {
            String accessToken = wxMaService.getAccessToken();
            log.info("获取到的accessToken:{}", accessToken);
            WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
            log.info("sessionInfo:{}", JSONObject.toJSONString(sessionInfo));
            String openid = sessionInfo.getOpenid();
            String unionid = sessionInfo.getUnionid();
            TUser tUser = userService.findByOpenId(openid);
            if (ObjectUtils.isEmpty(tUser)) {
                TUser newUser = new TUser();
                newUser.setWeixinOpenid(openid);
                newUser.setCreatetime(new Date());
                newUser.setUserName(RandomUtil.randomString(8));
                newUser.setIsvalid("Y");
                newUser.setAdmin("N");
                newUser.setUserorder((double) 0);
                newUser.setDepartid("1001");
                if (!StringUtils.isEmpty(sessionInfo.getUnionid())) {
                    newUser.setUnionId(unionid);
                }
                userService.insert(newUser);
                return ApiResult.success(userService.findByOpenId(openid));
            } else {
                if (!StringUtils.isEmpty(sessionInfo.getUnionid())) {
                    tUser.setUnionId(unionid);
                    userService.update(tUser);
                }
                return ApiResult.success(tUser);
            }
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }
    }

}
