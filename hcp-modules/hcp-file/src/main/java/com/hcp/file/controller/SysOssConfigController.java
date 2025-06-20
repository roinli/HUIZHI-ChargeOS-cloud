package com.hcp.file.controller;

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
import com.hcp.file.domain.SysOssConfig;
import com.hcp.file.service.ISysOssConfigService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 对象存储Controller
 *
 * @author hcp
 * @date 2024-08-07
 */
@RestController
@RequestMapping("/ossConfig")
public class SysOssConfigController extends BaseController
{
    @Autowired
    private ISysOssConfigService sysOssConfigService;

    /**
     * 查询对象存储列表
     */
    @RequiresPermissions("file:ossConfig:list")
    @GetMapping("/list")
    public AjaxResult list(SysOssConfig sysOssConfig)
    {
        IPage<SysOssConfig> list = sysOssConfigService.selectSysOssConfigPage(sysOssConfig);
        return AjaxResult.success(list);
    }

    /**
     * 导出对象存储列表
     */
    @RequiresPermissions("file:ossConfig:export")
    @Log(title = "对象存储", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOssConfig sysOssConfig)
    {
        List<SysOssConfig> list = sysOssConfigService.selectSysOssConfigList(sysOssConfig);
        ExcelUtil<SysOssConfig> util = new ExcelUtil<SysOssConfig>(SysOssConfig.class);
        util.exportExcel(response, list, "对象存储数据");
    }

    /**
     * 获取对象存储详细信息
     */
    @RequiresPermissions("file:ossConfig:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysOssConfigService.selectSysOssConfigById(id));
    }

    /**
     * 新增对象存储
     */
    @RequiresPermissions("file:ossConfig:add")
    @Log(title = "对象存储", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOssConfig sysOssConfig)
    {
        return toAjax(sysOssConfigService.insertSysOssConfig(sysOssConfig));
    }

    /**
     * 修改对象存储
     */
    @RequiresPermissions("file:ossConfig:edit")
    @Log(title = "对象存储", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOssConfig sysOssConfig)
    {
        return toAjax(sysOssConfigService.updateSysOssConfig(sysOssConfig));
    }

    /**
     * 删除对象存储
     */
    @RequiresPermissions("file:ossConfig:remove")
    @Log(title = "对象存储", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysOssConfigService.deleteSysOssConfigByIds(ids));
    }
}
