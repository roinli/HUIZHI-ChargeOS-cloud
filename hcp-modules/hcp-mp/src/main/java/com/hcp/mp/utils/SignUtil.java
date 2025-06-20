package com.hcp.mp.utils;

import com.hcp.common.core.exception.base.BaseException;
import com.hcp.common.redis.service.RedisService;
import com.hcp.mp.constant.XcxRedisKey;
import com.hcp.mp.domain.SignRequest;
import com.hcp.mp.service.IMiniappService;
import com.hcp.system.api.domain.Miniapp;
import com.hcp.system.api.utils.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SignUtil {
    private final static long MAX_SIGN_TIMEOUT = 24 * 3600;


    @Autowired
    @Lazy
    private IMiniappService miniappService;

    @Autowired
    @Lazy
    SignUtil selfUtil;

    @Autowired
    private RedisService redisService;


    /**
     * 校验token和sign
     * @param token         token值
     * @param signRequest   sign部分
     * @return 0 sign正确 token无效； -1 sign无效 -2时间戳异常  >0 返回UID sign token均有效
     */
    public long checkTokenAndSign(String token, SignRequest signRequest) throws BaseException
    {
        if(signRequest == null || TextUtils.isBlank(signRequest.getSign()) || TextUtils.isBlank(signRequest.getAppid())
                || TextUtils.isBlank(signRequest.getNoncecode()) || signRequest.getTm() == null){
            throw new BaseException("签名(DATA)验证失败");
        }
        //验证sign 防止数据爬取 由于采用https，不再过度约束内容一致性，因为HTTPS已经做了，从而增强处理效率
        Miniapp miniapp = miniappService.selectMiniappByAppId(signRequest.getAppid());
        if(miniapp == null){
            throw new BaseException("签名(APP)验证失败");
        }
        long tm = signRequest.getTm();
        long nowTm = System.currentTimeMillis() / 1000;

        if(Math.abs(tm - nowTm) > MAX_SIGN_TIMEOUT){
            throw new BaseException("签名(TM)验证失败");
        }
        String md5OriStr = "appkey=" + signRequest.getAppid()
                + "&noncecode=" + signRequest.getNoncecode()
                + "&tm=" + signRequest.getTm()
                + miniapp.getAppSecret();
        String md5Str = Md5Utils.getMD532Str(md5OriStr).toUpperCase();

        if(!md5Str.equals(signRequest.getSign())){
            throw new BaseException("签名校验失败");
        }

        //非获取uid场景（如登录验签）
        if(TextUtils.isBlank(token)){
            return 0L;
        }
        //其他场景顺便取下UID
        long uid =  selfUtil.token2Uid(token);
        if(uid > 0){
            signRequest.setOpUid(uid);
        }
        return uid;
    }


    public long token2Uid(String token){
        if(TextUtils.isBlank(token)){
            return 0;
        }
        String key = XcxRedisKey.USER_TOKEN + token;
        if(redisService.hasKey(key)){
            return Long.valueOf(redisService.getCacheObject(key).toString());
        }
        return 0L;
    }
}
