package com.hcp.operator.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hcp.common.log.annotation.Log;
import com.hcp.common.log.enums.BusinessType;
import com.hcp.common.security.annotation.RequiresPermissions;
import com.hcp.operator.domain.HlhtChargeOrderInfo;
import com.hcp.operator.service.IHlhtChargeOrderInfoService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 充电站充电订单信息Controller
 *
 * @author hcp
 * @date 2024-08-11
 */
@RestController
@RequestMapping("/orderInfo")
public class HlhtChargeOrderInfoController extends BaseController
{
    @Autowired
    private IHlhtChargeOrderInfoService hlhtChargeOrderInfoService;

    /**
     * 查询充电站充电订单信息列表
     */
    @RequiresPermissions("operator:orderInfo:list")
    @GetMapping("/list")
    public AjaxResult list(HlhtChargeOrderInfo hlhtChargeOrderInfo)
    {
        IPage<HlhtChargeOrderInfo> list = hlhtChargeOrderInfoService.selectHlhtChargeOrderInfoPage(hlhtChargeOrderInfo);
        return AjaxResult.success(list);
    }

    /**
     * 导出充电站充电订单信息列表
     */
    @RequiresPermissions("operator:orderInfo:export")
    @Log(title = "充电站充电订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HlhtChargeOrderInfo hlhtChargeOrderInfo)
    {
        List<HlhtChargeOrderInfo> list = hlhtChargeOrderInfoService.selectHlhtChargeOrderInfoList(hlhtChargeOrderInfo);
        ExcelUtil<HlhtChargeOrderInfo> util = new ExcelUtil<HlhtChargeOrderInfo>(HlhtChargeOrderInfo.class);
        util.exportExcel(response, list, "充电站充电订单信息数据");
    }

    /**
     * 获取充电站充电订单信息详细信息
     */
    @RequiresPermissions("operator:orderInfo:query")
    @GetMapping(value = "/{orderNo}")
    public AjaxResult getInfo(@PathVariable("orderNo") String orderNo)
    {
        return success(hlhtChargeOrderInfoService.selectHlhtChargeOrderInfoByOrderNo(orderNo));
    }

    /**
     * 新增充电站充电订单信息
     */
    @RequiresPermissions("operator:orderInfo:add")
    @Log(title = "充电站充电订单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HlhtChargeOrderInfo hlhtChargeOrderInfo)
    {
        return toAjax(hlhtChargeOrderInfoService.insertHlhtChargeOrderInfo(hlhtChargeOrderInfo));
    }

    /**
     * 修改充电站充电订单信息
     */
    @RequiresPermissions("operator:orderInfo:edit")
    @Log(title = "充电站充电订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HlhtChargeOrderInfo hlhtChargeOrderInfo)
    {
        return toAjax(hlhtChargeOrderInfoService.updateHlhtChargeOrderInfo(hlhtChargeOrderInfo));
    }

    /**
     * 删除充电站充电订单信息
     */
    @RequiresPermissions("operator:orderInfo:remove")
    @Log(title = "充电站充电订单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderNos}")
    public AjaxResult remove(@PathVariable String[] orderNos)
    {
        return toAjax(hlhtChargeOrderInfoService.deleteHlhtChargeOrderInfoByOrderNos(orderNos));
    }
}
