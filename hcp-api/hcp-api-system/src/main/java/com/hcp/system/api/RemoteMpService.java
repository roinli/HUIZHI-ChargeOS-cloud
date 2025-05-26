package com.hcp.system.api;

import com.hcp.common.core.constant.ServiceNameConstants;
import com.hcp.common.core.domain.R;
import com.hcp.system.api.domain.Miniapp;
import com.hcp.system.api.factory.RemoteMpFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务
 *
 * @author vctgo
 */
@FeignClient(contextId = "remoteMpService", value = ServiceNameConstants.MP_SERVICE, fallbackFactory = RemoteMpFallbackFactory.class)
public interface RemoteMpService
{
    /**
     * 获取小程序信息
     * @param appId 小程序appId
     * @return 结果
     */
    @GetMapping("/mp/info/{appId}")
    R<Miniapp> getMiniAppInfo(@PathVariable("appId") String appId);

}
