package com.hcp.system.api.factory;

import com.hcp.common.core.domain.R;
import com.hcp.system.api.RemoteMpService;
import com.hcp.system.api.domain.Miniapp;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteMpFallbackFactory implements FallbackFactory<RemoteMpService> {
    @Override
    public RemoteMpService create(Throwable cause) {
        return new RemoteMpService() {
            @Override
            public R<Miniapp> getMiniAppInfo(String appId) {
                return R.fail("获取小程序信息失败"+cause.getMessage());
            }
        };
    }
}
