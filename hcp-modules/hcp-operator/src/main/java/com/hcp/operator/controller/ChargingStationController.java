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
import com.hcp.system.api.domain.ChargingStation;
import com.hcp.operator.service.IChargingStationService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 站点Controller
 *
 * @author hcp
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/station")
public class ChargingStationController extends BaseController
{
    @Autowired
    private IChargingStationService chargingStationService;

    /**
     * 查询站点列表
     */
    @RequiresPermissions("operator:station:list")
    @GetMapping("/list")
    public AjaxResult list(ChargingStation chargingStation)
    {
        IPage<ChargingStation> list = chargingStationService.selectChargingStationPage(chargingStation);
        return AjaxResult.success(list);
    }

    /**
     * 导出站点列表
     */
    @RequiresPermissions("operator:station:export")
    @Log(title = "站点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChargingStation chargingStation)
    {
        List<ChargingStation> list = chargingStationService.selectChargingStationList(chargingStation);
        ExcelUtil<ChargingStation> util = new ExcelUtil<ChargingStation>(ChargingStation.class);
        util.exportExcel(response, list, "站点数据");
    }

    /**
     * 获取站点详细信息
     */
    @RequiresPermissions("operator:station:query")
    @GetMapping(value = "/{stationId}")
    public AjaxResult getInfo(@PathVariable("stationId") Long stationId)
    {
        return success(chargingStationService.selectChargingStationByStationId(stationId));
    }

    /**
     * 新增站点
     */
    @RequiresPermissions("operator:station:add")
    @Log(title = "站点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChargingStation chargingStation)
    {
        return toAjax(chargingStationService.insertChargingStation(chargingStation));
    }

    /**
     * 修改站点
     */
    @RequiresPermissions("operator:station:edit")
    @Log(title = "站点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChargingStation chargingStation)
    {
        return toAjax(chargingStationService.updateChargingStation(chargingStation));
    }

    /**
     * 删除站点
     */
    @RequiresPermissions("operator:station:remove")
    @Log(title = "站点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stationIds}")
    public AjaxResult remove(@PathVariable Long[] stationIds)
    {
        return toAjax(chargingStationService.deleteChargingStationByStationIds(stationIds));
    }
}
