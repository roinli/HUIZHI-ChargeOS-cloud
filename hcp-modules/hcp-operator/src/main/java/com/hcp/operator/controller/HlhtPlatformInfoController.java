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
import com.hcp.operator.domain.HlhtPlatformInfo;
import com.hcp.operator.service.IHlhtPlatformInfoService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 平台信息Controller
 *
 * @author hcp
 * @date 2024-08-11
 */
@RestController
@RequestMapping("/platformInfo")
public class HlhtPlatformInfoController extends BaseController
{
    @Autowired
    private IHlhtPlatformInfoService hlhtPlatformInfoService;

    /**
     * 查询平台信息列表
     */
    @RequiresPermissions("operator:platformInfo:list")
    @GetMapping("/list")
    public AjaxResult list(HlhtPlatformInfo hlhtPlatformInfo)
    {
        IPage<HlhtPlatformInfo> list = hlhtPlatformInfoService.selectHlhtPlatformInfoPage(hlhtPlatformInfo);
        return AjaxResult.success(list);
    }

    /**
     * 导出平台信息列表
     */
    @RequiresPermissions("operator:platformInfo:export")
    @Log(title = "平台信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HlhtPlatformInfo hlhtPlatformInfo)
    {
        List<HlhtPlatformInfo> list = hlhtPlatformInfoService.selectHlhtPlatformInfoList(hlhtPlatformInfo);
        ExcelUtil<HlhtPlatformInfo> util = new ExcelUtil<HlhtPlatformInfo>(HlhtPlatformInfo.class);
        util.exportExcel(response, list, "平台信息数据");
    }

    /**
     * 获取平台信息详细信息
     */
    @RequiresPermissions("operator:platformInfo:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hlhtPlatformInfoService.selectHlhtPlatformInfoById(id));
    }

    /**
     * 新增平台信息
     */
    @RequiresPermissions("operator:platformInfo:add")
    @Log(title = "平台信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HlhtPlatformInfo hlhtPlatformInfo)
    {
        return toAjax(hlhtPlatformInfoService.insertHlhtPlatformInfo(hlhtPlatformInfo));
    }

    /**
     * 修改平台信息
     */
    @RequiresPermissions("operator:platformInfo:edit")
    @Log(title = "平台信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HlhtPlatformInfo hlhtPlatformInfo)
    {
        return toAjax(hlhtPlatformInfoService.updateHlhtPlatformInfo(hlhtPlatformInfo));
    }

    /**
     * 删除平台信息
     */
    @RequiresPermissions("operator:platformInfo:remove")
    @Log(title = "平台信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hlhtPlatformInfoService.deleteHlhtPlatformInfoByIds(ids));
    }
}
