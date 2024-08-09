package com.jingli.inter.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.alibaba.fastjson2.JSONObject;
import com.jingli.inter.constant.Constance;
import com.jingli.inter.entity.TUser;
import com.jingli.inter.mapper.TUserMapper;
import com.jingli.inter.service.TUserService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户表;(t_user)表服务实现类
 */
@Service
public class TUserServiceImpl implements TUserService {
    private Logger logger = LoggerFactory.getLogger(TUserServiceImpl.class);
    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private WxMaService wxMaService;
    @Override
    public int insert(TUser tUser) {
        return tUserMapper.insert(tUser);
    }

    @Override
    public int update(TUser tUser) {
        return tUserMapper.update(tUser);
    }

    //
    @Override
     public TUser findByOpenId(String searchOpenId) {
         TUser tUser = tUserMapper.findByOpenId(searchOpenId);
         return tUser;
     }
     @Override
     public String appletBindMobile(String searchOpenId, String code) {
         try {
             String accessToken = wxMaService.getAccessToken();
             logger.info("用户:{},Code:{},accessToken:{}",searchOpenId,code,accessToken);
             WxMaPhoneNumberInfo phoneNoInfo = wxMaService.getUserService().getPhoneNoInfo(code);
             logger.info("用户:{},phoneNoInfo:{}",searchOpenId, JSONObject.toJSONString(phoneNoInfo));
             if (!ObjectUtils.isEmpty(phoneNoInfo)){
                 String phoneNumber = phoneNoInfo.getPhoneNumber();
                 TUser tUser = tUserMapper.findByOpenId(searchOpenId);
                 tUser.setMobile(phoneNumber);
                 tUserMapper.update(tUser);
                 return Constance.TEXT_SUCCESS;
             }else {
                 return Constance.TEXT_ERROR;
             }
         } catch (WxErrorException e) {
             throw new RuntimeException(e);
         }
     }

}