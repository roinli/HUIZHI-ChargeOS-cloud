package com.hcp.system.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.constant.ServiceNameConstants;
import com.hcp.common.core.domain.R;
import com.hcp.system.api.domain.ChargingOrder;
import com.hcp.system.api.domain.ChargingPort;
import com.hcp.system.api.domain.OrderLog;
import com.hcp.system.api.factory.RemoteChargeOrderFallbackFactory;
import com.hcp.system.api.factory.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户服务
 *
 * @author vctgo
 */
@FeignClient(contextId = "remoteChargeOrderService", value = ServiceNameConstants.OPERATOR_SERVICE)
public interface RemoteChargeOrderService
{
    /**
     * 根据订单编号查询订单信息
     *
     * @param orderNumber 订单编号
     * @return 结果
     */
    @GetMapping("/order/getOrderByOrderNumber")
    R<ChargingOrder> getOrderByOrderNumber(@RequestParam("orderNumber") String orderNumber);

    /**
     * 根据订单编号查询订单日志列表
     * @param orderNumber
     * @return
     */
    @GetMapping("/order/queryOrderLogByOrderNumber")
    R<List<OrderLog>> queryOrderLogByOrderNumber( @RequestParam("orderNumber") String orderNumber);

    @PostMapping("/order/queryOrderList")
    R<Page<ChargingOrder>>  queryOrderList(@RequestBody ChargingOrder chargingOrder);
}
