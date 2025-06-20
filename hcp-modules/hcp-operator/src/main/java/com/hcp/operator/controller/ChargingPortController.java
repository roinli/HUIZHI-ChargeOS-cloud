package com.hcp.operator.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hcp.system.api.domain.ChargingPort;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hcp.common.log.annotation.Log;
import com.hcp.common.log.enums.BusinessType;
import com.hcp.common.security.annotation.RequiresPermissions;
import com.hcp.operator.service.IChargingPortService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 充电桩端口Controller
 *
 * @author hcp
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/port")
@Tag(name = "充电桩端口接口")
public class ChargingPortController extends BaseController
{
    @Autowired
    private IChargingPortService chargingPortService;

    /**
     * 查询充电桩端口列表
     */
    @RequiresPermissions("operator:order:list")
    @GetMapping("/list")
    @Operation(summary = "查询充电桩端口列表")
    public AjaxResult list(ChargingPort chargingPort)
    {
        IPage<ChargingPort> list = chargingPortService.selectChargingPortPage(chargingPort);
        return AjaxResult.success(list);
    }

    /**
     * 导出充电桩端口列表
     */
    @RequiresPermissions("operator:port:export")
    @Log(title = "充电桩端口", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChargingPort chargingPort)
    {
        List<ChargingPort> list = chargingPortService.selectChargingPortList(chargingPort);
        ExcelUtil<ChargingPort> util = new ExcelUtil<ChargingPort>(ChargingPort.class);
        util.exportExcel(response, list, "充电桩端口数据");
    }

    /**
     * 获取充电桩端口详细信息
     */
    @RequiresPermissions("operator:port:query")
    @GetMapping(value = "/{portId}")
    public AjaxResult getInfo(@PathVariable("portId") Long portId)
    {
        return success(chargingPortService.selectChargingPortByPortId(portId));
    }

    /**
     * 新增充电桩端口
     */
    @RequiresPermissions("operator:port:add")
    @Log(title = "充电桩端口", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChargingPort chargingPort)
    {
        return toAjax(chargingPortService.insertChargingPort(chargingPort));
    }

    /**
     * 修改充电桩端口
     */
    @RequiresPermissions("operator:port:edit")
    @Log(title = "充电桩端口", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChargingPort chargingPort)
    {
        return toAjax(chargingPortService.updateChargingPort(chargingPort));
    }

    /**
     * 删除充电桩端口
     */
    @RequiresPermissions("operator:port:remove")
    @Log(title = "充电桩端口", businessType = BusinessType.DELETE)
	@DeleteMapping("/{portIds}")
    public AjaxResult remove(@PathVariable Long[] portIds)
    {
        return toAjax(chargingPortService.deleteChargingPortByPortIds(portIds));
    }

    @RequiresPermissions("operator:port:edit")
    @Log(title = "端口开启或关闭", businessType = BusinessType.UPDATE)
    @PostMapping("/switchPort")
    @ApiImplicitParam(paramType = "header",name = "token",value = "身份认证Token")
    public AjaxResult switchPort(@ApiParam("端口id")@RequestParam Integer id, @ApiParam("开启1，关闭0")@RequestParam Integer type){

        return chargingPortService.switchPort(id,type);
    }

    /**
     * 获取充电桩端口详细信息
     */
    @GetMapping(value = "/info/{portId}")
    public AjaxResult portInfo(@PathVariable("portId") Long portId)
    {
        return AjaxResult.success(chargingPortService.selectChargingPortByPortId(portId));
    }
}
