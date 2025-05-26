package com.hcp.job.service;

import java.util.List;

import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.job.domain.XxlJobLog;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 定时任务日志Service接口
 *
 * @author vctgo
 * @date 2023-01-03
 */
public interface IXxlJobLogService
{
    /**
     * 查询定时任务日志
     *
     * @param id 定时任务日志主键
     * @return 定时任务日志
     */
    XxlJobLog selectXxlJobLogById(Long id);

    /**
     * 查询定时任务日志列表-分页
     *
     * @param xxlJobLog 定时任务日志
     * @return 定时任务日志集合
     */
    IPage<XxlJobLog> selectXxlJobLogPage(XxlJobLog xxlJobLog);
    /**
     * 查询定时任务日志列表
     *
     * @param xxlJobLog 定时任务日志
     * @return 定时任务日志集合
     */
    List<XxlJobLog> selectXxlJobLogList(XxlJobLog xxlJobLog);

    /**
     * 新增定时任务日志
     *
     * @param xxlJobLog 定时任务日志
     * @return 结果
     */
    int insertXxlJobLog(XxlJobLog xxlJobLog);

    /**
     * 修改定时任务日志
     *
     * @param xxlJobLog 定时任务日志
     * @return 结果
     */
    int updateXxlJobLog(XxlJobLog xxlJobLog);

    /**
     * 批量删除定时任务日志
     *
     * @param ids 需要删除的定时任务日志主键集合
     * @return 结果
     */
    int deleteXxlJobLogByIds(Long[] ids);

    /**
     * 删除定时任务日志信息
     *
     * @param id 定时任务日志主键
     * @return 结果
     */
    int deleteXxlJobLogById(Long id);

    /**
     * 清理日志
     * @param log
     * @return
     */
    AjaxResult clearLog(XxlJobLog log);
}
