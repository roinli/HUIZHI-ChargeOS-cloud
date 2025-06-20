package com.hcp.operator.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hcp.common.core.context.SecurityContextHolder;
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
import com.hcp.system.api.domain.ChargingPile;
import com.hcp.operator.service.IChargingPileService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 充电桩Controller
 *
 * @author hcp
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/pile")
public class ChargingPileController extends BaseController
{
    @Autowired
    private IChargingPileService chargingPileService;

    /**
     * 查询充电桩列表
     */
    @RequiresPermissions("operator:pile:list")
    @GetMapping("/list")
    public AjaxResult list(ChargingPile chargingPile)
    {
        IPage<ChargingPile> list = chargingPileService.selectChargingPilePage(chargingPile);
        return AjaxResult.success(list);
    }

    /**
     * 导出充电桩列表
     */
    @RequiresPermissions("operator:pile:export")
    @Log(title = "充电桩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChargingPile chargingPile)
    {
        List<ChargingPile> list = chargingPileService.selectChargingPileList(chargingPile);
        ExcelUtil<ChargingPile> util = new ExcelUtil<ChargingPile>(ChargingPile.class);
        util.exportExcel(response, list, "充电桩数据");
    }

    /**
     * 获取充电桩详细信息
     */
    @RequiresPermissions("operator:pile:query")
    @GetMapping(value = "/{pileId}")
    public AjaxResult getInfo(@PathVariable("pileId") String pileId)
    {
        return success(chargingPileService.selectChargingPileByPileId(pileId));
    }

    /**
     * 新增充电桩
     */
    @RequiresPermissions("operator:pile:add")
    @Log(title = "充电桩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChargingPile chargingPile)
    {
        chargingPile.setUserId(SecurityContextHolder.getUserId());
        return toAjax(chargingPileService.insertChargingPile(chargingPile));
    }

    /**
     * 修改充电桩
     */
    @RequiresPermissions("operator:pile:edit")
    @Log(title = "充电桩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChargingPile chargingPile)
    {
        return toAjax(chargingPileService.updateChargingPile(chargingPile));
    }

    /**
     * 删除充电桩
     */
    @RequiresPermissions("operator:pile:remove")
    @Log(title = "充电桩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pileIds}")
    public AjaxResult remove(@PathVariable String[] pileIds)
    {
        return toAjax(chargingPileService.deleteChargingPileByPileIds(pileIds));
    }
}
