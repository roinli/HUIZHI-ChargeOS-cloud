package com.hcp.job.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.hcp.common.core.utils.StringUtils;
import com.hcp.job.core.thread.JobScheduleHelper;
import com.hcp.job.core.thread.JobTriggerPoolHelper;
import com.hcp.job.core.trigger.TriggerTypeEnum;
import com.hcp.job.core.util.I18nUtil;
import com.hcp.job.domain.XxlJobLog;
import com.xxl.job.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.hcp.job.domain.XxlJobInfo;
import com.hcp.job.service.IXxlJobInfoService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 任务Controller
 *
 * @author vctgo
 */
@RestController
@RequestMapping("/info")
public class XxlJobInfoController extends BaseController
{
    private final Logger logger = LoggerFactory.getLogger(XxlJobInfoController.class);

    @Resource
    private IXxlJobInfoService xxlJobInfoService;

    /**
     * 查询任务列表
     */
    @RequiresPermissions("job:info:list")
    @GetMapping("/list")
    public AjaxResult list(XxlJobInfo xxlJobInfo)
    {
        IPage<XxlJobInfo> list = xxlJobInfoService.selectXxlJobInfoPage(xxlJobInfo);
        return AjaxResult.success(list);
    }

    /**
     * 导出任务列表
     */
    @RequiresPermissions("job:info:export")
    @Log(title = "任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XxlJobInfo xxlJobInfo)
    {
        List<XxlJobInfo> list = xxlJobInfoService.selectXxlJobInfoList(xxlJobInfo);
        ExcelUtil<XxlJobInfo> util = new ExcelUtil<>(XxlJobInfo.class);
        util.exportExcel(response, list, "任务数据");
    }

    /**
     * 获取任务详细信息
     */
    @RequiresPermissions("job:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(xxlJobInfoService.selectXxlJobInfoById(id));
    }

    /**
     * 新增任务
     */
    @RequiresPermissions("job:info:add")
    @Log(title = "任务", businessType = BusinessType.INSERT)
    @PostMapping(value = "add")
    public AjaxResult add(@RequestBody XxlJobInfo xxlJobInfo)
    {
        return xxlJobInfoService.add(xxlJobInfo);
    }

    /**
     * 修改任务
     */
    @RequiresPermissions("job:info:edit")
    @Log(title = "任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XxlJobInfo xxlJobInfo)
    {
        return toAjax(xxlJobInfoService.updateXxlJobInfo(xxlJobInfo));
    }

    /**
     * 删除任务
     */
    @RequiresPermissions("job:info:remove")
    @Log(title = "任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xxlJobInfoService.deleteXxlJobInfoByIds(ids));
    }


    /**
     * 执行任务
     */
    @RequiresPermissions("job:info:trigger")
    @Log(title = "执行任务", businessType = BusinessType.OTHER)
    @PostMapping("/trigger")
    public AjaxResult trigger(@RequestBody XxlJobLog xxlJobLog)
    {
        if (StringUtils.isEmpty(xxlJobLog.getId())) {
            return toAjax(false);
        }
        String executorParam = xxlJobLog.getExecutorParam();
        String addressList = xxlJobLog.getExecutorAddress();
        int id = xxlJobLog.getId().intValue();
        JobTriggerPoolHelper.trigger(id, TriggerTypeEnum.MANUAL, -1, null, executorParam, addressList);
        return toAjax(true);
    }

    /**
     * 开启任务
     */
    @RequiresPermissions("job:info:start")
    @Log(title = "开启任务", businessType = BusinessType.INSERT)
    @GetMapping(value = "/start/{id}")
    public AjaxResult start(@PathVariable(value = "id") Integer id)
    {
        return xxlJobInfoService.start(id);
    }

    /**
     * 停止任务
     */
    @RequiresPermissions("job:info:stop")
    @Log(title = "停止任务", businessType = BusinessType.INSERT)
    @GetMapping(value = "/stop/{id}")
    public AjaxResult stop(@PathVariable(value = "id") Integer id)
    {
        return xxlJobInfoService.stop(id);
    }

    /**
     * 下次执行时间
     */
    @RequiresPermissions("job:info:nextTriggerTime")
    @Log(title = "下次执行时间", businessType = BusinessType.INSERT)
    @PostMapping(value = "/nextTriggerTime")
    public AjaxResult nextTriggerTime(@RequestBody XxlJobInfo paramXxlJobInfo)
    {
        List<String> result = new ArrayList<>();
        try {
            Date lastTime = new Date();
            for (int i = 0; i < 5; i++) {
                lastTime = JobScheduleHelper.generateNextValidTime(paramXxlJobInfo, lastTime);
                if (lastTime != null) {
                    result.add(DateUtil.formatDateTime(lastTime));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            error((I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid")) + e.getMessage());
        }
        return AjaxResult.success(result);
    }
}
