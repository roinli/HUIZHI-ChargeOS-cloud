package com.hcp.job.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
import com.hcp.job.domain.XxlJobRegistry;
import com.hcp.job.service.IXxlJobRegistryService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 注册Controller
 *
 * @author vctgo
 */
@RestController
@RequestMapping("/registry")
public class XxlJobRegistryController extends BaseController
{
    @Resource
    private IXxlJobRegistryService xxlJobRegistryService;

    /**
     * 查询注册列表
     */
    @RequiresPermissions("job:registry:list")
    @GetMapping("/list")
    public AjaxResult list(XxlJobRegistry xxlJobRegistry)
    {
        IPage<XxlJobRegistry> list = xxlJobRegistryService.selectXxlJobRegistryPage(xxlJobRegistry);
        return AjaxResult.success(list);
    }

    /**
     * 导出注册列表
     */
    @RequiresPermissions("job:registry:export")
    @Log(title = "注册", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XxlJobRegistry xxlJobRegistry)
    {
        List<XxlJobRegistry> list = xxlJobRegistryService.selectXxlJobRegistryList(xxlJobRegistry);
        ExcelUtil<XxlJobRegistry> util = new ExcelUtil<XxlJobRegistry>(XxlJobRegistry.class);
        util.exportExcel(response, list, "注册数据");
    }

    /**
     * 获取注册详细信息
     */
    @RequiresPermissions("job:registry:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xxlJobRegistryService.selectXxlJobRegistryById(id));
    }

    /**
     * 新增注册
     */
    @RequiresPermissions("job:registry:add")
    @Log(title = "注册", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XxlJobRegistry xxlJobRegistry)
    {
        return toAjax(xxlJobRegistryService.insertXxlJobRegistry(xxlJobRegistry));
    }

    /**
     * 修改注册
     */
    @RequiresPermissions("job:registry:edit")
    @Log(title = "注册", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XxlJobRegistry xxlJobRegistry)
    {
        return toAjax(xxlJobRegistryService.updateXxlJobRegistry(xxlJobRegistry));
    }

    /**
     * 删除注册
     */
    @RequiresPermissions("job:registry:remove")
    @Log(title = "注册", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xxlJobRegistryService.deleteXxlJobRegistryByIds(ids));
    }
}
