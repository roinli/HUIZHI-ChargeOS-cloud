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
import com.hcp.operator.domain.HlhtEquipmentInfo;
import com.hcp.operator.service.IHlhtEquipmentInfoService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 设备列表Controller
 *
 * @author hcp
 * @date 2024-08-11
 */
@RestController
@RequestMapping("/equipmentInfo")
public class HlhtEquipmentInfoController extends BaseController
{
    @Autowired
    private IHlhtEquipmentInfoService hlhtEquipmentInfoService;

    /**
     * 查询设备列表列表
     */
    @RequiresPermissions("operator:equipmentInfo:list")
    @GetMapping("/list")
    public AjaxResult list(HlhtEquipmentInfo hlhtEquipmentInfo)
    {
        IPage<HlhtEquipmentInfo> list = hlhtEquipmentInfoService.selectHlhtEquipmentInfoPage(hlhtEquipmentInfo);
        return AjaxResult.success(list);
    }

    /**
     * 导出设备列表列表
     */
    @RequiresPermissions("operator:equipmentInfo:export")
    @Log(title = "设备列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HlhtEquipmentInfo hlhtEquipmentInfo)
    {
        List<HlhtEquipmentInfo> list = hlhtEquipmentInfoService.selectHlhtEquipmentInfoList(hlhtEquipmentInfo);
        ExcelUtil<HlhtEquipmentInfo> util = new ExcelUtil<HlhtEquipmentInfo>(HlhtEquipmentInfo.class);
        util.exportExcel(response, list, "设备列表数据");
    }

    /**
     * 获取设备列表详细信息
     */
    @RequiresPermissions("operator:equipmentInfo:query")
    @GetMapping(value = "/{equipmentID}")
    public AjaxResult getInfo(@PathVariable("equipmentID") String equipmentID)
    {
        return success(hlhtEquipmentInfoService.selectHlhtEquipmentInfoByEquipmentID(equipmentID));
    }

    /**
     * 新增设备列表
     */
    @RequiresPermissions("operator:equipmentInfo:add")
    @Log(title = "设备列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HlhtEquipmentInfo hlhtEquipmentInfo)
    {
        return toAjax(hlhtEquipmentInfoService.insertHlhtEquipmentInfo(hlhtEquipmentInfo));
    }

    /**
     * 修改设备列表
     */
    @RequiresPermissions("operator:equipmentInfo:edit")
    @Log(title = "设备列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HlhtEquipmentInfo hlhtEquipmentInfo)
    {
        return toAjax(hlhtEquipmentInfoService.updateHlhtEquipmentInfo(hlhtEquipmentInfo));
    }

    /**
     * 删除设备列表
     */
    @RequiresPermissions("operator:equipmentInfo:remove")
    @Log(title = "设备列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{equipmentIDs}")
    public AjaxResult remove(@PathVariable String[] equipmentIDs)
    {
        return toAjax(hlhtEquipmentInfoService.deleteHlhtEquipmentInfoByEquipmentIDs(equipmentIDs));
    }
}
