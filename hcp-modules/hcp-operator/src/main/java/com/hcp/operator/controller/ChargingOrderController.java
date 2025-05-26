package com.hcp.operator.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.domain.R;
import com.hcp.common.security.utils.SecurityUtils;
import com.hcp.operator.domain.City;
import com.hcp.operator.service.IOrderLogService;
import com.hcp.system.api.domain.OrderLog;
import com.hcp.system.api.domain.SysUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hcp.common.log.annotation.Log;
import com.hcp.common.log.enums.BusinessType;
import com.hcp.common.security.annotation.RequiresPermissions;
import com.hcp.system.api.domain.ChargingOrder;
import com.hcp.operator.service.IChargingOrderService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 充电订单Controller
 *
 * @author hcp
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/order")
public class ChargingOrderController extends BaseController
{
    @Autowired
    private IChargingOrderService chargingOrderService;

    @Resource
    IOrderLogService orderLogService;

    /**
     * 查询充电订单列表
     */
    @RequiresPermissions("operator:order:list")
    @GetMapping("/list")
    public AjaxResult list(ChargingOrder chargingOrder)
    {
        Long userId = SecurityUtils.getUserId();
        // 非管理员或未选择用户 则查询当前用户数据
        if (!SysUser.isAdmin(userId))
        {
            chargingOrder.setUserId(userId);
        }
        IPage<ChargingOrder> list = chargingOrderService.selectChargingOrderPage(chargingOrder);
        return AjaxResult.success(list);
    }

    /**
     * 导出充电订单列表
     */
    @RequiresPermissions("operator:order:export")
    @Log(title = "充电订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChargingOrder chargingOrder)
    {
        Long userId = SecurityUtils.getUserId();
        // 非管理员或未选择用户 则查询当前用户数据
        if (!SysUser.isAdmin(userId))
        {
            chargingOrder.setUserId(userId);
        }
        List<ChargingOrder> list = chargingOrderService.selectChargingOrderList(chargingOrder);
        ExcelUtil<ChargingOrder> util = new ExcelUtil<ChargingOrder>(ChargingOrder.class);
        util.exportExcel(response, list, "充电订单数据");
    }

    /**
     * 获取充电订单详细信息
     */
    @RequiresPermissions("operator:order:query")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") String orderId)
    {
        return success(chargingOrderService.selectChargingOrderByOrderId(orderId));
    }

//    @RequiresPermissions("operator:order:query")
    @GetMapping(value = "getOrderByOrderNumber/{orderNumber}")
        public AjaxResult getOrderByOrderNumber(@PathVariable("orderNumber") String orderNumber)
    {
        return chargingOrderService.selectChargingOrderByOrderNumber(orderNumber);
    }

    /**
     * 新增充电订单
     */
    @RequiresPermissions("operator:order:add")
    @Log(title = "充电订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChargingOrder chargingOrder)
    {
        return toAjax(chargingOrderService.insertChargingOrder(chargingOrder));
    }

    /**
     * 修改充电订单
     */
    @RequiresPermissions("operator:order:edit")
    @Log(title = "充电订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChargingOrder chargingOrder)
    {
        return toAjax(chargingOrderService.updateChargingOrder(chargingOrder));
    }

    /**
     * 删除充电订单
     */
    @RequiresPermissions("operator:order:remove")
    @Log(title = "充电订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable String[] orderIds)
    {
        return toAjax(chargingOrderService.deleteChargingOrderByOrderIds(orderIds));
    }

    @ApiOperation("根据订单编号获取订单日志")
    @GetMapping("/queryOrderLogByOrderNumber")
    public R<List<OrderLog>> list(@RequestParam("orderNumber") String orderNumber)
    {
        List<OrderLog> orderLogList = orderLogService.queryOrderLogByOrderNumber(orderNumber);
        return R.ok(orderLogList);
    }

    /**
     * 查询充电订单列表
     */
    @PostMapping("/queryOrderList")
    public R<Page<ChargingOrder>> queryOrderList(@RequestBody ChargingOrder chargingOrder)
    {
        Page<ChargingOrder> list = chargingOrderService.queryOrderList(chargingOrder);
        return R.ok(list);
    }
}
