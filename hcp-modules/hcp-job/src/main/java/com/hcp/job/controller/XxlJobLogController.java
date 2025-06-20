package com.hcp.job.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hcp.job.core.scheduler.XxlJobScheduler;
import com.xxl.job.core.biz.ExecutorBiz;
import com.xxl.job.core.biz.model.LogParam;
import com.xxl.job.core.biz.model.LogResult;
import com.xxl.job.core.biz.model.ReturnT;
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
import com.hcp.job.domain.XxlJobLog;
import com.hcp.job.service.IXxlJobLogService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 定时任务日志Controller
 *
 * @author vctgo
 */
@RestController
@RequestMapping("/log")
public class XxlJobLogController extends BaseController
{
    @Autowired
    private IXxlJobLogService xxlJobLogService;

    /**
     * 查询定时任务日志列表
     */
    @RequiresPermissions("job:log:list")
    @GetMapping("/list")
    public AjaxResult list(XxlJobLog xxlJobLog)
    {
        IPage<XxlJobLog> list = xxlJobLogService.selectXxlJobLogPage(xxlJobLog);
        return AjaxResult.success(list);
    }

    /**
     * 导出定时任务日志列表
     */
    @RequiresPermissions("job:log:export")
    @Log(title = "定时任务日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XxlJobLog xxlJobLog)
    {
        List<XxlJobLog> list = xxlJobLogService.selectXxlJobLogList(xxlJobLog);
        ExcelUtil<XxlJobLog> util = new ExcelUtil<>(XxlJobLog.class);
        util.exportExcel(response, list, "定时任务日志数据");
    }

    /**
     * 获取定时任务日志详细信息
     */
    @RequiresPermissions("job:log:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xxlJobLogService.selectXxlJobLogById(id));
    }

    /**
     * 新增定时任务日志
     */
    @RequiresPermissions("job:log:add")
    @Log(title = "定时任务日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XxlJobLog xxlJobLog)
    {
        return toAjax(xxlJobLogService.insertXxlJobLog(xxlJobLog));
    }

    /**
     * 修改定时任务日志
     */
    @RequiresPermissions("job:log:edit")
    @Log(title = "定时任务日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XxlJobLog xxlJobLog)
    {
        return toAjax(xxlJobLogService.updateXxlJobLog(xxlJobLog));
    }

    /**
     * 删除定时任务日志
     */
    @RequiresPermissions("job:log:remove")
    @Log(title = "定时任务日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xxlJobLogService.deleteXxlJobLogByIds(ids));
    }

    /**
     *定时任务日志
     */
    @Log(title = "定时任务日志", businessType = BusinessType.OTHER)
    @PostMapping("/logDetailCat")
    public AjaxResult logDetailCat(@RequestBody XxlJobLog log) {
        try {
            ExecutorBiz executorBiz = XxlJobScheduler.getExecutorBiz(log.getExecutorAddress());
            ReturnT<LogResult> logResult = executorBiz.log(new LogParam(log.getLogDateTim(), log.getId(), log.getFromLineNum()));
            if (logResult.getContent()!=null && logResult.getContent().getFromLineNum() > logResult.getContent().getToLineNum()) {
                XxlJobLog jobLog = xxlJobLogService.selectXxlJobLogById(log.getId());
                if (jobLog.getHandleCode() > 0) {
                    logResult.getContent().setEnd(true);
                }
            }
            return logResult.getCode() == 200 ? AjaxResult.success(logResult.getContent()) : AjaxResult.error(logResult.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.error();
    }

    @RequiresPermissions("job:log:clearLog")
    @Log(title = "清理定时任务日志", businessType = BusinessType.DELETE)
    @PostMapping("/clearLog")
    public AjaxResult clearLog(@RequestBody XxlJobLog log) {
        return xxlJobLogService.clearLog(log);
    }

}
