package com.hcp.system.api.factory;

import com.hcp.common.core.domain.R;
import com.hcp.system.api.RemoteSimulatorService;
import com.hcp.system.api.RemoteUserService;
import com.hcp.system.api.domain.ChargingOrder;
import com.hcp.system.api.domain.SysUser;
import com.hcp.system.api.model.LoginUser;
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
public class RemoteSimulatorFallbackFactory implements FallbackFactory<RemoteSimulatorService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteSimulatorFallbackFactory.class);

    @Override
    public RemoteSimulatorService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteSimulatorService()
        {

            @Override
            public R<String> startCharge(ChargingOrder chargingOrder) {
                return R.fail("启动模拟器充电失败:" + throwable.getMessage());
            }

            @Override
            public R<String> stopCharge(String pileId, String deviceId) {
                return R.fail("停止模拟器启动失败:" + throwable.getMessage());
            }
        };
    }
}
