package com.hcp.job.service.impl;

import java.util.Date;
import java.util.List;

import com.hcp.common.core.utils.StringUtils;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.job.core.cron.CronExpression;
import com.hcp.job.core.route.ExecutorRouteStrategyEnum;
import com.hcp.job.core.scheduler.MisfireStrategyEnum;
import com.hcp.job.core.scheduler.ScheduleTypeEnum;
import com.hcp.job.core.thread.JobScheduleHelper;
import com.hcp.job.core.util.I18nUtil;
import com.hcp.job.domain.XxlJobGroup;
import com.hcp.job.mapper.XxlJobGroupMapper;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.job.core.glue.GlueTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.hcp.job.mapper.XxlJobInfoMapper;
import com.hcp.job.domain.XxlJobInfo;
import com.hcp.job.service.IXxlJobInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;

import javax.annotation.Resource;

import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * 任务Service业务层处理
 *
 * @author vctgo
 * @date 2022-12-31
 */
@Service
public class XxlJobInfoServiceImpl implements IXxlJobInfoService
{
    private static Logger logger = LoggerFactory.getLogger(XxlJobInfoServiceImpl.class);
    @Resource
    private XxlJobInfoMapper xxlJobInfoMapper;
    @Resource
    private XxlJobGroupMapper xxlJobGroupMapper;

    /**
     * 查询任务
     *
     * @param id 任务主键
     * @return 任务
     */
    @Override
    public XxlJobInfo selectXxlJobInfoById(Integer id)
    {
        return xxlJobInfoMapper.getById(id);
    }

    /**
     * 查询任务列表-分页
     *
     * @param xxlJobInfo 任务
     * @return 任务
     */
    @Override
    public IPage<XxlJobInfo> selectXxlJobInfoPage(XxlJobInfo xxlJobInfo)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return xxlJobInfoMapper.selectXxlJobInfoList(mpPage,xxlJobInfo);
    }
    /**
     * 查询任务列表
     *
     * @param xxlJobInfo 任务
     * @return 任务
     */
    @Override
    public List<XxlJobInfo> selectXxlJobInfoList(XxlJobInfo xxlJobInfo)
    {
        return xxlJobInfoMapper.selectXxlJobInfoList(xxlJobInfo);
    }

    /**
     * 新增任务
     *
     * @param xxlJobInfo 任务
     * @return 结果
     */

    @Override
    public int insertXxlJobInfo(XxlJobInfo xxlJobInfo)
    {
        return xxlJobInfoMapper.insert(xxlJobInfo);
    }

    /**
     * 修改任务
     *
     * @param xxlJobInfo 任务
     * @return 结果
     */
    @Override
    public int updateXxlJobInfo(XxlJobInfo xxlJobInfo)
    {
        return xxlJobInfoMapper.updateOne(xxlJobInfo);
    }

    /**
     * 批量删除任务
     *
     * @param ids 需要删除的任务主键
     * @return 结果
     */
    @Override
    public int deleteXxlJobInfoByIds(Long[] ids)
    {
        return xxlJobInfoMapper.deleteXxlJobInfoByIds(ids);
    }

    /**
     * 删除任务信息
     *
     * @param id 任务主键
     * @return 结果
     */
    @Override
    public int deleteXxlJobInfoById(Long id)
    {
        return xxlJobInfoMapper.deleteById(id);
    }

    /**
     * 开启任务
     * @param id
     * @return
     */
    @Override
    public AjaxResult start(Integer id) {
        XxlJobInfo xxlJobInfo = xxlJobInfoMapper.getById(id);
        // valid
        ScheduleTypeEnum scheduleTypeEnum = ScheduleTypeEnum.match(xxlJobInfo.getScheduleType(), ScheduleTypeEnum.NONE);
        if (ScheduleTypeEnum.NONE == scheduleTypeEnum) {
            return AjaxResult.error(I18nUtil.getString("schedule_type_none_limit_start"));
        }
        // next trigger time (5s后生效，避开预读周期)
        long nextTriggerTime = 0;
        try {
            Date nextValidTime = JobScheduleHelper.generateNextValidTime(xxlJobInfo, new Date(System.currentTimeMillis() + JobScheduleHelper.PRE_READ_MS));
            if (nextValidTime == null) {
                return AjaxResult.error((I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid")));
            }
            nextTriggerTime = nextValidTime.getTime();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResult.error((I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid")));
        }

        xxlJobInfo.setTriggerStatus(1);
        xxlJobInfo.setTriggerLastTime(0);
        xxlJobInfo.setTriggerNextTime(nextTriggerTime);

        xxlJobInfo.setUpdateTime(new Date());
        xxlJobInfoMapper.updateOne(xxlJobInfo);
        return AjaxResult.success();
    }

    /**
     * 停止任务
     * @param id
     * @return
     */
    @Override
    public AjaxResult stop(Integer id) {
        XxlJobInfo xxlJobInfo = xxlJobInfoMapper.getById(id);

        xxlJobInfo.setTriggerStatus(0);
        xxlJobInfo.setTriggerLastTime(0);
        xxlJobInfo.setTriggerNextTime(0);

        xxlJobInfo.setUpdateTime(new Date());
        xxlJobInfoMapper.updateOne(xxlJobInfo);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult add(XxlJobInfo jobInfo) {
        // valid base
        XxlJobGroup group = xxlJobGroupMapper.load(jobInfo.getJobGroup());
        String msg = "";
        if (group == null) {
            msg = I18nUtil.getString("system_please_choose")+I18nUtil.getString("jobinfo_field_jobgroup");
        }
        if (jobInfo.getJobDesc()==null || jobInfo.getJobDesc().trim().length()==0) {
            msg = I18nUtil.getString("system_please_input")+I18nUtil.getString("jobinfo_field_jobdesc");
        }
        if (jobInfo.getAuthor()==null || jobInfo.getAuthor().trim().length()==0) {
            msg = I18nUtil.getString("system_please_input")+I18nUtil.getString("jobinfo_field_author");
        }
        // valid trigger
        ScheduleTypeEnum scheduleTypeEnum = ScheduleTypeEnum.match(jobInfo.getScheduleType(), null);
        if (scheduleTypeEnum == null) {
            msg = I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid");
        }
        if (scheduleTypeEnum == ScheduleTypeEnum.CRON) {
            if (jobInfo.getScheduleConf()==null || !CronExpression.isValidExpression(jobInfo.getScheduleConf())) {
                msg = "Cron"+I18nUtil.getString("system_unvalid");
            }
        } else if (scheduleTypeEnum == ScheduleTypeEnum.FIX_RATE) {
            if (jobInfo.getScheduleConf() == null) {
                msg = I18nUtil.getString("schedule_type");
            }
            try {
                int fixSecond = Integer.valueOf(jobInfo.getScheduleConf());
                if (fixSecond < 1) {
                    msg = I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid");
                }
            } catch (Exception e) {
                msg = I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid");
            }
        }

        // valid job
        if (GlueTypeEnum.match(jobInfo.getGlueType()) == null) {
            msg = I18nUtil.getString("jobinfo_field_gluetype")+I18nUtil.getString("system_unvalid");
        }
        if (GlueTypeEnum.BEAN==GlueTypeEnum.match(jobInfo.getGlueType()) && (jobInfo.getExecutorHandler()==null || jobInfo.getExecutorHandler().trim().length()==0) ) {
            msg = I18nUtil.getString("system_please_input")+"JobHandler";
        }
        if (GlueTypeEnum.GLUE_SHELL==GlueTypeEnum.match(jobInfo.getGlueType()) && jobInfo.getGlueSource()!=null) {
            jobInfo.setGlueSource(jobInfo.getGlueSource().replaceAll("\r", ""));
        }

        // valid advanced
        if (ExecutorRouteStrategyEnum.match(jobInfo.getExecutorRouteStrategy(), null) == null) {
            msg = I18nUtil.getString("jobinfo_field_executorRouteStrategy")+I18nUtil.getString("system_unvalid");
        }
        if (MisfireStrategyEnum.match(jobInfo.getMisfireStrategy(), null) == null) {
            msg = I18nUtil.getString("misfire_strategy")+I18nUtil.getString("system_unvalid");
        }
        if (ExecutorBlockStrategyEnum.match(jobInfo.getExecutorBlockStrategy(), null) == null) {
            msg = I18nUtil.getString("jobinfo_field_executorBlockStrategy")+I18nUtil.getString("system_unvalid");
        }

        // 》ChildJobId valid
        if (jobInfo.getChildJobId()!=null && jobInfo.getChildJobId().trim().length()>0) {
            String[] childJobIds = jobInfo.getChildJobId().split(",");
            for (String childJobIdItem: childJobIds) {
                if (childJobIdItem!=null && childJobIdItem.trim().length()>0 && isNumeric(childJobIdItem)) {
                    XxlJobInfo childJobInfo = xxlJobInfoMapper.loadById(Integer.parseInt(childJobIdItem));
                    if (childJobInfo==null) {
                        msg = (I18nUtil.getString("jobinfo_field_childJobId")+"({0})"+I18nUtil.getString("system_not_found"));
                    }
                } else {
                    msg = (I18nUtil.getString("jobinfo_field_childJobId")+"({0})"+I18nUtil.getString("system_unvalid"));
                }
            }

            // join , avoid "xxx,,"
            String temp = "";
            for (String item:childJobIds) {
                temp += item + ",";
            }
            temp = temp.substring(0, temp.length()-1);
            jobInfo.setChildJobId(temp);
        }

        // add in db
        jobInfo.setAddTime(new Date());
        jobInfo.setUpdateTime(new Date());
        jobInfo.setGlueUpdatetime(new Date());
        if (jobInfo.getTriggerStatus() == null) {
            jobInfo.setTriggerStatus(1);
        }
        xxlJobInfoMapper.save(jobInfo);
        if (jobInfo.getId() < 1) {
            msg = I18nUtil.getString("jobinfo_field_add")+I18nUtil.getString("system_fail");
        }
        if (StringUtils.isNotEmpty(msg)) {
            return AjaxResult.error(msg);
        }
        return AjaxResult.success();
    }
}
