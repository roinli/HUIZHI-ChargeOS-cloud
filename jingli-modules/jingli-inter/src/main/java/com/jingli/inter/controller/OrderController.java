package com.jingli.inter.controller;

import com.jingli.inter.domain.ApiResult;
import com.jingli.inter.domain.PageVo;
import com.jingli.inter.entity.ChargingPile;
import com.jingli.inter.entity.ChargingPort;
import com.jingli.inter.service.ChargingOrderService;
import com.jingli.inter.service.ChargingPileService;
import com.jingli.inter.service.ChargingPortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outer/order")
public class OrderController {
    @Autowired
    private ChargingOrderService chargingOrderService;
    @Autowired
    private ChargingPortService chargingPortService;
    @Autowired
    private ChargingPileService chargingPileService;

    @GetMapping("/saveOrder")
    public ApiResult<String> saveOrder(@RequestParam("search_openId") String search_openId,
                                       @RequestParam("order_order_type") String order_order_type,
                                       @RequestParam("order_port_id") String order_port_id,
                                       @RequestParam("order_hour") String order_hour,
                                       @RequestHeader("mobile") String mobile,
                                       @RequestHeader("platform") String platform) {

        ChargingPort chargingPort = chargingPortService.querySwitchPortByPortId(order_port_id);
        String chargingPileNo = String.valueOf(chargingPort.getParkid());
        ChargingPile chargingPile = chargingPileService.queryChargingPile(chargingPileNo);
        return chargingOrderService.saveOrder(search_openId, order_order_type, order_port_id, order_hour, chargingPileNo, chargingPile, mobile, platform);
    }

    @GetMapping("/orderList")
    public ApiResult<PageVo> orderList(@RequestParam(value = "search_openId", required = false) String search_openId,
                                       @RequestParam(value = "search_page", required = false) String search_page,
                                       @RequestParam(value = "search_orderStatus", required = false) String search_orderStatus,
                                       @RequestHeader(value = "mobile", required = false) String mobile) {
        return chargingOrderService.queryOrderList(search_openId, search_page, search_orderStatus, mobile);
    }

}
