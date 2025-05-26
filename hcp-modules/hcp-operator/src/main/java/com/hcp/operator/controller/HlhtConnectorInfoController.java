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
import com.hcp.operator.domain.HlhtConnectorInfo;
import com.hcp.operator.service.IHlhtConnectorInfoService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 接口信息Controller
 *
 * @author hcp
 * @date 2024-08-11
 */
@RestController
@RequestMapping("/connectorInfo")
public class HlhtConnectorInfoController extends BaseController
{
    @Autowired
    private IHlhtConnectorInfoService hlhtConnectorInfoService;

    /**
     * 查询接口信息列表
     */
    @RequiresPermissions("operator:connectorInfo:list")
    @GetMapping("/list")
    public AjaxResult list(HlhtConnectorInfo hlhtConnectorInfo)
    {
        IPage<HlhtConnectorInfo> list = hlhtConnectorInfoService.selectHlhtConnectorInfoPage(hlhtConnectorInfo);
        return AjaxResult.success(list);
    }

    /**
     * 导出接口信息列表
     */
    @RequiresPermissions("operator:connectorInfo:export")
    @Log(title = "接口信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HlhtConnectorInfo hlhtConnectorInfo)
    {
        List<HlhtConnectorInfo> list = hlhtConnectorInfoService.selectHlhtConnectorInfoList(hlhtConnectorInfo);
        ExcelUtil<HlhtConnectorInfo> util = new ExcelUtil<HlhtConnectorInfo>(HlhtConnectorInfo.class);
        util.exportExcel(response, list, "接口信息数据");
    }

    /**
     * 获取接口信息详细信息
     */
    @RequiresPermissions("operator:connectorInfo:query")
    @GetMapping(value = "/{connectorId}")
    public AjaxResult getInfo(@PathVariable("connectorId") String connectorId)
    {
        return success(hlhtConnectorInfoService.selectHlhtConnectorInfoByConnectorId(connectorId));
    }

    /**
     * 新增接口信息
     */
    @RequiresPermissions("operator:connectorInfo:add")
    @Log(title = "接口信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HlhtConnectorInfo hlhtConnectorInfo)
    {
        return toAjax(hlhtConnectorInfoService.insertHlhtConnectorInfo(hlhtConnectorInfo));
    }

    /**
     * 修改接口信息
     */
    @RequiresPermissions("operator:connectorInfo:edit")
    @Log(title = "接口信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HlhtConnectorInfo hlhtConnectorInfo)
    {
        return toAjax(hlhtConnectorInfoService.updateHlhtConnectorInfo(hlhtConnectorInfo));
    }

    /**
     * 删除接口信息
     */
    @RequiresPermissions("operator:connectorInfo:remove")
    @Log(title = "接口信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{connectorIds}")
    public AjaxResult remove(@PathVariable String[] connectorIds)
    {
        return toAjax(hlhtConnectorInfoService.deleteHlhtConnectorInfoByConnectorIds(connectorIds));
    }
}
