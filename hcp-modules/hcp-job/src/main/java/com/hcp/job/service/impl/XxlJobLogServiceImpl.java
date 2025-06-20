package com.hcp.job.service.impl;

import java.util.Date;
import java.util.List;

import com.hcp.common.core.web.domain.AjaxResult;
import com.xxl.job.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.job.mapper.XxlJobLogMapper;
import com.hcp.job.domain.XxlJobLog;
import com.hcp.job.service.IXxlJobLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 定时任务日志Service业务层处理
 *
 * @author vctgo
 * @date 2023-01-03
 */
@Service
public class XxlJobLogServiceImpl implements IXxlJobLogService
{
    @Autowired
    private XxlJobLogMapper xxlJobLogMapper;

    /**
     * 查询定时任务日志
     *
     * @param id 定时任务日志主键
     * @return 定时任务日志
     */
    @Override
    public XxlJobLog selectXxlJobLogById(Long id)
    {
        return xxlJobLogMapper.load(id);
    }

    /**
     * 查询定时任务日志列表-分页
     *
     * @param xxlJobLog 定时任务日志
     * @return 定时任务日志
     */
    @Override
    public IPage<XxlJobLog> selectXxlJobLogPage(XxlJobLog xxlJobLog)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return xxlJobLogMapper.selectXxlJobLogList(mpPage,xxlJobLog);
    }
    /**
     * 查询定时任务日志列表
     *
     * @param xxlJobLog 定时任务日志
     * @return 定时任务日志
     */
    @Override
    public List<XxlJobLog> selectXxlJobLogList(XxlJobLog xxlJobLog)
    {
        return xxlJobLogMapper.selectXxlJobLogList(xxlJobLog);
    }

    /**
     * 新增定时任务日志
     *
     * @param xxlJobLog 定时任务日志
     * @return 结果
     */

    @Override
    public int insertXxlJobLog(XxlJobLog xxlJobLog)
    {
        return xxlJobLogMapper.insert(xxlJobLog);
    }

    /**
     * 修改定时任务日志
     *
     * @param xxlJobLog 定时任务日志
     * @return 结果
     */
    @Override
    public int updateXxlJobLog(XxlJobLog xxlJobLog)
    {
        return xxlJobLogMapper.updateById(xxlJobLog);
    }

    /**
     * 批量删除定时任务日志
     *
     * @param ids 需要删除的定时任务日志主键
     * @return 结果
     */
    @Override
    public int deleteXxlJobLogByIds(Long[] ids)
    {
        return xxlJobLogMapper.deleteXxlJobLogByIds(ids);
    }

    /**
     * 删除定时任务日志信息
     *
     * @param id 定时任务日志主键
     * @return 结果
     */
    @Override
    public int deleteXxlJobLogById(Long id)
    {
        return xxlJobLogMapper.deleteById(id);
    }

    /**
     * 清理日志
     * @param log
     * @return
     */
    @Override
    public AjaxResult clearLog(XxlJobLog log)
    {
        int jobGroup = log.getJobGroup() == null ? 0 : log.getJobGroup();
        int jobId = log.getJobId() == null ? 0 : log.getJobId();
        int type = Integer.valueOf(log.getType());
        Date clearBeforeTime = null;
        int clearBeforeNum = 0;
        if (type == 1) {
            clearBeforeTime = DateUtil.addMonths(new Date(), -1);	// 清理一个月之前日志数据
        } else if (type == 2) {
            clearBeforeTime = DateUtil.addMonths(new Date(), -3);	// 清理三个月之前日志数据
        } else if (type == 3) {
            clearBeforeTime = DateUtil.addMonths(new Date(), -6);	// 清理六个月之前日志数据
        } else if (type == 4) {
            clearBeforeTime = DateUtil.addYears(new Date(), -1);	// 清理一年之前日志数据
        } else if (type == 5) {
            clearBeforeNum = 1000;		// 清理一千条以前日志数据
        } else if (type == 6) {
            clearBeforeNum = 10000;		// 清理一万条以前日志数据
        } else if (type == 7) {
            clearBeforeNum = 30000;		// 清理三万条以前日志数据
        } else if (type == 8) {
            clearBeforeNum = 100000;	// 清理十万条以前日志数据
        } else if (type == 9) {
            clearBeforeNum = 0;			// 清理所有日志数据
        } else {
            return AjaxResult.error("Clean type is illegal");
        }
        List<Long> logIds = null;
        do {
            logIds = xxlJobLogMapper.findClearLogIds(jobGroup, jobId, clearBeforeTime, clearBeforeNum, 1000);
            if (logIds!=null && logIds.size()>0) {
                xxlJobLogMapper.clearLog(logIds);
            }
        } while (logIds!=null && logIds.size()>0);
        return AjaxResult.success();
    }
}
