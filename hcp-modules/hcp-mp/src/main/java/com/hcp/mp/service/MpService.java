package com.hcp.mp.service;


import com.hcp.common.core.domain.R;
import com.hcp.mp.ro.BindPhoneRo;
import com.hcp.mp.ro.UserInfoRo;

public interface MpService {

    R loginByCode(String appid, String code);

    R phoneInfo(String appid, String token, BindPhoneRo bindPhoneRo);

    R update(Long uid, UserInfoRo userInfoRo);

    R userInfo(Long uid);


    /**
     * 小程序绑定手机号码
     * @param openId
     * @param code
     * @param appId
     * @return
     */
    R appletBindMobile(String openId, String code, String appId);
}
