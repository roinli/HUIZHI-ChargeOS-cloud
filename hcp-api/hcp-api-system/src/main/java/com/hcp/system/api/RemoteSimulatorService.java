package com.hcp.system.api;

import com.hcp.common.core.constant.ServiceNameConstants;
import com.hcp.common.core.domain.R;
import com.hcp.system.api.domain.ChargingOrder;
import com.hcp.system.api.factory.RemoteSimulatorFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 模拟器服务
 *
 * @author vctgo
 */
@FeignClient(contextId = "remoteSimulatorService", value = ServiceNameConstants.SIMULATOR_SERVICE, fallbackFactory = RemoteSimulatorFallbackFactory.class)
public interface RemoteSimulatorService
{
    //启动模拟器充电
    @GetMapping(value = "/evcs/sim/v1/startCharge")
    public R<String> startCharge(ChargingOrder chargingOrder);

    //停止模拟器充电
    @GetMapping(value = "/evcs/sim/v1/endCharge")
    public R<String> stopCharge(@RequestParam("pileId")String pileId,@RequestParam("deviceId")String deviceId);
}
