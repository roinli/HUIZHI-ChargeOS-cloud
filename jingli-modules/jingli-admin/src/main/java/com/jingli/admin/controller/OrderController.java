
package com.jingli.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.commons.BaseController;
import com.jingli.admin.commons.Response;
import com.jingli.admin.model.OrderQueryForm;
import com.jingli.admin.model.OrderResult;
import com.jingli.admin.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.jingli.admin.constant.Constant.PLAT_FORM;

/**
 * @ClassName ChargingOrderController
 * @Description: 订单控制器
 * @author Zfenor
 **/
@Api(tags = "4 订单管理")
@RestController
@RequestMapping("/chargingorder")
public class OrderController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService chargingOrderService;


    @ApiOperation("获取订单列表")
    @GetMapping("getOrderList")
    @ApiImplicitParam(paramType = "header", name = "token", value = "身份认证Token")
    public Response<IPage<OrderResult>> getOrderList(OrderQueryForm orderQueryForm,
                                                     @ApiParam("页数") @RequestParam("pageNo") int pageNo,
                                                     @ApiParam("每页显示数") @RequestParam("pageSize") int pageSize) {
        /*设置当前代理商*/
        orderQueryForm.setUserId(getUserId().toString());
        /*分页查询订单列表*/
        Page<OrderResult> page = new Page<>(pageNo, pageSize);
        Map<String, Object> params = new HashMap<>(4);
        params.put("order", orderQueryForm);
        params.put(PLAT_FORM, getPlatForm());
        IPage<OrderResult> orderList = chargingOrderService.getOrderList(params, page);
        return Response.ok(orderList);
    }
}
