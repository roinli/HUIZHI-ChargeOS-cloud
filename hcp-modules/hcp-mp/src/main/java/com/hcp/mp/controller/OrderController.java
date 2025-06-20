package com.hcp.mp.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.domain.R;
import com.hcp.common.core.exception.ServiceException;
import com.hcp.common.security.utils.SecurityUtils;
import com.hcp.system.api.RemoteChargeOrderService;
import com.hcp.system.api.RemoteChargePortService;
import com.hcp.system.api.RemoteChargingService;
import com.hcp.system.api.domain.ChargingOrder;
import com.hcp.system.api.domain.ChargingPort;
import com.hcp.system.api.domain.OrderLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private RemoteChargingService remoteChargingService;

    @Resource
    private RemoteChargePortService remoteChargePortService;

    @Resource
    private RemoteChargeOrderService remoteChargeOrderService;


    @GetMapping("/saveOrder")
    public R<ChargingOrder> saveOrder(
            @RequestParam(value = "orderType", required = false) String orderType,
            @RequestParam("portId") Long portId,
            @RequestParam("amount") BigDecimal amount,
            @RequestParam("hour") Integer hour,
            @RequestParam("userId") Long userId
    ) {

//        Long userId = SecurityUtils.getUserId();
        R<ChargingPort> chargingPortR = remoteChargePortService.getChargePortByPortId(portId);
        if (ObjectUtil.isEmpty(chargingPortR) || ObjectUtil.isEmpty(chargingPortR.getData())) {
            throw new ServiceException("端口查询失败!");
        }
        ChargingPort chargingPort = chargingPortR.getData();

        ChargingOrder order = remoteChargingService.startCharge(chargingPort.getPileId(), chargingPort.getDeviceId(), userId, amount, hour).getData();

        return R.ok(order);

    }

    @GetMapping("/queryOrderList")
    public R<Page<ChargingOrder>> queryOrderList(@RequestParam(value = "orderStatus", required = false) String orderStatus,
                                             @RequestParam(value = "pageNo") Integer pageNo,
                                             @RequestParam(value = "pageSize") Integer pageSize,
                                             @RequestParam("userId") Long userId) {
//        Long userId = SecurityUtils.getUserId();
        ChargingOrder chargingOrder = new ChargingOrder();
        chargingOrder.setOrderState(orderStatus);
        chargingOrder.setUserId(userId);
        chargingOrder.setPageNo(pageNo);
        chargingOrder.setPageSize(pageSize);
        R<Page<ChargingOrder>> orderList = remoteChargeOrderService.queryOrderList(chargingOrder);
        return orderList;
    }

    @GetMapping("/orderDetail")
    public R<ChargingOrder> orderDetail(
            @RequestParam("orderNumber") String orderNumber) {
        ChargingOrder chargingOrder = remoteChargeOrderService.getOrderByOrderNumber(orderNumber).getData();
        return R.ok(chargingOrder);
    }

    @GetMapping("/orderTrace")
    public R<List<OrderLog>> orderTrace(
                                        @RequestParam("orderNumber") String orderNumber) {

        List<OrderLog> orderLogs = remoteChargeOrderService.queryOrderLogByOrderNumber(orderNumber).getData();
        return R.ok(orderLogs);
    }

    @GetMapping("/endCharge")
    public R<String> endCharge(@RequestParam("pileId") String pileId,
                               @RequestParam("port") String port,
                               @RequestParam(value = "orderNumber", required = false) String orderNumber) {

        R<ChargingPort> chargePortByPortId = remoteChargePortService.getChargePortByPortId(Long.valueOf(port));
        if (ObjectUtil.isEmpty(chargePortByPortId)) {
            return R.fail("充电口信息未找到");
        }
        String deviceId = chargePortByPortId.getData().getDeviceId();
        String data = remoteChargingService.stopCharge(pileId, deviceId).getData();
        return R.ok();
    }

}
