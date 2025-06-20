package com.hcp.modules.monitor.controller;

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
import com.hcp.modules.monitor.domain.MonitorSys;
import com.hcp.modules.monitor.service.IMonitorSysService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 服务器管理Controller
 *
 * @author vctgo
 * @date 2022-04-26
 */
@RestController
@RequestMapping("/sys")
public class MonitorSysController extends BaseController
{
    @Autowired
    private IMonitorSysService monitorSysService;

    /**
     * 查询服务器管理列表
     */
    @RequiresPermissions("monitor:sys:list")
    @GetMapping("/list")
    public AjaxResult list(MonitorSys monitorSys)
    {
        IPage<MonitorSys> list = monitorSysService.selectMonitorSysPage(monitorSys);
        return AjaxResult.success(list);
    }

    /**
     * 导出服务器管理列表
     */
    @RequiresPermissions("monitor:sys:export")
    @Log(title = "服务器管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MonitorSys monitorSys)
    {
        List<MonitorSys> list = monitorSysService.selectMonitorSysList(monitorSys);
        ExcelUtil<MonitorSys> util = new ExcelUtil<MonitorSys>(MonitorSys.class);
        util.exportExcel(response, list, "服务器管理数据");
    }

    /**
     * 获取服务器管理详细信息
     */
    @RequiresPermissions("monitor:sys:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(monitorSysService.selectMonitorSysById(id));
    }

    /**
     * 新增服务器管理
     */
    @RequiresPermissions("monitor:sys:add")
    @Log(title = "服务器管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MonitorSys monitorSys)
    {
        return toAjax(monitorSysService.insertMonitorSys(monitorSys));
    }

    /**
     * 修改服务器管理
     */
    @RequiresPermissions("monitor:sys:edit")
    @Log(title = "服务器管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MonitorSys monitorSys)
    {
        return toAjax(monitorSysService.updateMonitorSys(monitorSys));
    }

    /**
     * 删除服务器管理
     */
    @RequiresPermissions("monitor:sys:remove")
    @Log(title = "服务器管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(monitorSysService.deleteMonitorSysByIds(ids));
    }

    /**
     * 测试链接
     */
    @PostMapping("/testlink")
    public AjaxResult testlink(@RequestBody MonitorSys monitorSys)
    {
        return monitorSysService.testLink(monitorSys);
    }

}
