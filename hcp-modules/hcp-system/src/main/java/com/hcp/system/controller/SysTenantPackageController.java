package com.hcp.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hcp.system.domain.SysSimplePackage;
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
import com.hcp.system.domain.SysTenantPackage;
import com.hcp.system.service.ISysTenantPackageService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;

/**
 * 租户套餐Controller
 *
 * @author vctgo
 * @date 2022-03-25
 */
@RestController
@RequestMapping("/tenantpackage")
public class SysTenantPackageController extends BaseController
{
    @Autowired
    private ISysTenantPackageService sysTenantPackageService;

    /**
     * 查询租户套餐列表
     */
    @RequiresPermissions("system:tenantpackage:list")
    @GetMapping("/list")
    public AjaxResult list(SysTenantPackage sysTenantPackage)
    {
        IPage<SysTenantPackage> list = sysTenantPackageService.selectSysTenantPackageList(sysTenantPackage);
        return AjaxResult.success(list);
    }

    /**
     * 查询租户套餐精简列表
     */
    @GetMapping("/get-simple-list")
    public AjaxResult getSimpleList()
    {
        List<SysSimplePackage> list = sysTenantPackageService.getSimpleList();
        return AjaxResult.success(list);
    }


    /**
     * 导出租户套餐列表
     */
    @RequiresPermissions("system:tenantpackage:export")
    @Log(title = "租户套餐", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTenantPackage sysTenantPackage)
    {
        List<SysTenantPackage> list = sysTenantPackageService.selectSysTenantPackageExport(sysTenantPackage);
        ExcelUtil<SysTenantPackage> util = new ExcelUtil<SysTenantPackage>(SysTenantPackage.class);
        util.exportExcel(response, list, "租户套餐数据");
    }

    /**
     * 获取租户套餐详细信息
     */
    @RequiresPermissions("system:tenantpackage:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysTenantPackageService.selectSysTenantPackageById(id));
    }

    /**
     * 新增租户套餐
     */
    @RequiresPermissions("system:tenantpackage:add")
    @Log(title = "租户套餐", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTenantPackage sysTenantPackage)
    {
        return toAjax(sysTenantPackageService.insertSysTenantPackage(sysTenantPackage));
    }

    /**
     * 修改租户套餐
     */
    @RequiresPermissions("system:tenantpackage:edit")
    @Log(title = "租户套餐", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTenantPackage sysTenantPackage)
    {
        return sysTenantPackageService.updateSysTenantPackage(sysTenantPackage);
    }

    /**
     * 删除租户套餐
     */
    @RequiresPermissions("system:tenantpackage:remove")
    @Log(title = "租户套餐", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return sysTenantPackageService.deleteSysTenantPackageByIds(ids);
    }
}
