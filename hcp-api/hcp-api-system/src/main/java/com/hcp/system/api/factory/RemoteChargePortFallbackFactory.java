package com.hcp.system.api.factory;

import com.hcp.common.core.domain.R;
import com.hcp.system.api.RemoteChargePortService;
import com.hcp.system.api.domain.ChargingPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @author vctgo
 */
@Component
public class RemoteChargePortFallbackFactory implements FallbackFactory<RemoteChargePortService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteChargePortFallbackFactory.class);

    @Override
    public RemoteChargePortService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteChargePortService()
        {
            @Override
            public R<ChargingPort> getChargePortByPortId(Long portId) {
                return R.fail("获取端口信息是失败:" + throwable.getMessage());
            }

        };
    }
}
