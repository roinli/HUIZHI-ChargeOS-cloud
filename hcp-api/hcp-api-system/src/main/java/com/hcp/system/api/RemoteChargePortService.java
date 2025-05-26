package com.hcp.system.api;

import com.hcp.common.core.constant.ServiceNameConstants;
import com.hcp.common.core.domain.R;
import com.hcp.system.api.domain.ChargingPort;
import com.hcp.system.api.factory.RemoteChargePortFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务
 *
 * @author vctgo
 */
@FeignClient(contextId = "RemoteChargePortService", value = ServiceNameConstants.OPERATOR_SERVICE)
public interface RemoteChargePortService
{
    /**
     * 根据端口id查询充电桩端口信息
     *
     * @param portId 端口id
     * @return 结果
     */
    @GetMapping("/port/info/{portId}")
    R<ChargingPort> getChargePortByPortId(@PathVariable("portId") Long portId);

}
