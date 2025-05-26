package com.hcp.demo.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hcp.demo.domain.Demo;
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
import com.hcp.demo.service.IDemoService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 测试Controller
 *
 * @author vctgo
 * @date 2022-04-11
 */
@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController
{
    @Autowired
    private IDemoService DemoService;

    /**
     * 查询测试列表
     */
    @RequiresPermissions("demo:demo:list")
    @GetMapping("/list")
    public AjaxResult list(Demo demo)
    {
        IPage<Demo> list = DemoService.selectDemoPage(demo);
        return AjaxResult.success(list);
    }

    /**
     * 导出测试列表
     */
    @RequiresPermissions("demo:demo:export")
    @Log(title = "测试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Demo demo)
    {
        List<Demo> list = DemoService.selectDemoList(demo);
        ExcelUtil<Demo> util = new ExcelUtil<Demo>(Demo.class);
        util.exportExcel(response, list, "测试数据");
    }

    /**
     * 获取测试详细信息
     */
    @RequiresPermissions("demo:demo:query")
    @GetMapping(value = "/{demoId}")
    public AjaxResult getInfo(@PathVariable("demoId") Long demoId)
    {
        return AjaxResult.success(DemoService.selectDemoByDemoId(demoId));
    }

    /**
     * 新增测试
     */
    @RequiresPermissions("demo:demo:add")
    @Log(title = "测试", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Demo demo)
    {
        return toAjax(DemoService.insertDemo(demo));
    }

    /**
     * 修改测试
     */
    @RequiresPermissions("demo:demo:edit")
    @Log(title = "测试", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Demo demo)
    {
        return toAjax(DemoService.updateDemo(demo));
    }

    /**
     * 删除测试
     */
    @RequiresPermissions("demo:demo:remove")
    @Log(title = "测试", businessType = BusinessType.DELETE)
	@DeleteMapping("/{demoIds}")
    public AjaxResult remove(@PathVariable Long[] demoIds)
    {
        return toAjax(DemoService.deleteDemoByDemoIds(demoIds));
    }
}
