
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
 * @Author yangbin
 **/
@Api(tags = "A4 订单管理")
@RestController
@RequestMapping("/sysOrder")
public class SysOrderController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(SysOrderController.class);
    @Autowired
    private OrderService chargingOrderService;

    @ApiOperation("获取订单列表")
    @GetMapping("/getOrderList")
    @ApiImplicitParam(paramType = "header", name = "token", value = "身份认证Token")
    public Response<IPage<OrderResult>> getOrderList(@ApiParam("订单状态") @RequestParam(value = "orderState", required = false) String orderState,
                                                     @ApiParam("分销商Id") @RequestParam(value = "userId", required = false) String userId,
                                                     @ApiParam("用户名或订单号") @RequestParam(value = "userOrNum", required = false) String userOrNum,
                                                     @ApiParam("端口号") @RequestParam(value = "portCode", required = false) String portCode,
                                                     @ApiParam("页数") @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                     @ApiParam("开始时间") @RequestParam(value = "startTime", required = false) String startTime,
                                                     @ApiParam("结束时间") @RequestParam(value = "endTime", required = false) String endTime,
                                                     @ApiParam("每页显示数") @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("获取订单列表");
        Map<String, Object> params = new HashMap<>(16);
        params.put("orderState", orderState);
        params.put("userId", userId);
        params.put("userOrNum", userOrNum);
        // 端口编号  88-16
        params.put("portCode", portCode);
        params.put("order", new OrderQueryForm());
        params.put("startTime1", startTime);
        params.put("endTime", endTime);
        params.put(PLAT_FORM, getPlatForm());
        /*分页查询订单列表*/
        Page<OrderResult> page = new Page<>(pageNo, pageSize);
        IPage<OrderResult> orderList = chargingOrderService.getOrderList(params, page);
        return Response.ok(orderList);
    }

}
