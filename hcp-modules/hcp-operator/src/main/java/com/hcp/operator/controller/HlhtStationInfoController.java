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
import com.hcp.operator.domain.HlhtStationInfo;
import com.hcp.operator.service.IHlhtStationInfoService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 站点信息Controller
 *
 * @author hcp
 * @date 2024-08-11
 */
@RestController
@RequestMapping("/stationInfo")
public class HlhtStationInfoController extends BaseController
{
    @Autowired
    private IHlhtStationInfoService hlhtStationInfoService;

    /**
     * 查询站点信息列表
     */
    @RequiresPermissions("operator:stationInfo:list")
    @GetMapping("/list")
    public AjaxResult list(HlhtStationInfo hlhtStationInfo)
    {
        IPage<HlhtStationInfo> list = hlhtStationInfoService.selectHlhtStationInfoPage(hlhtStationInfo);
        return AjaxResult.success(list);
    }

    /**
     * 导出站点信息列表
     */
    @RequiresPermissions("operator:stationInfo:export")
    @Log(title = "站点信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HlhtStationInfo hlhtStationInfo)
    {
        List<HlhtStationInfo> list = hlhtStationInfoService.selectHlhtStationInfoList(hlhtStationInfo);
        ExcelUtil<HlhtStationInfo> util = new ExcelUtil<HlhtStationInfo>(HlhtStationInfo.class);
        util.exportExcel(response, list, "站点信息数据");
    }

    /**
     * 获取站点信息详细信息
     */
    @RequiresPermissions("operator:stationInfo:query")
    @GetMapping(value = "/{stationId}")
    public AjaxResult getInfo(@PathVariable("stationId") String stationId)
    {
        return success(hlhtStationInfoService.selectHlhtStationInfoByStationId(stationId));
    }

    /**
     * 新增站点信息
     */
    @RequiresPermissions("operator:stationInfo:add")
    @Log(title = "站点信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HlhtStationInfo hlhtStationInfo)
    {
        return toAjax(hlhtStationInfoService.insertHlhtStationInfo(hlhtStationInfo));
    }

    /**
     * 修改站点信息
     */
    @RequiresPermissions("operator:stationInfo:edit")
    @Log(title = "站点信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HlhtStationInfo hlhtStationInfo)
    {
        return toAjax(hlhtStationInfoService.updateHlhtStationInfo(hlhtStationInfo));
    }

    /**
     * 删除站点信息
     */
    @RequiresPermissions("operator:stationInfo:remove")
    @Log(title = "站点信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stationIds}")
    public AjaxResult remove(@PathVariable String[] stationIds)
    {
        return toAjax(hlhtStationInfoService.deleteHlhtStationInfoByStationIds(stationIds));
    }
}
