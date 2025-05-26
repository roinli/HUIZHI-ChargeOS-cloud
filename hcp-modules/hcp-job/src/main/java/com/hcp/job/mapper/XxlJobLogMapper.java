package com.hcp.job.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hcp.job.domain.XxlJobLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 定时任务日志Mapper接口
 *
 * @author vctgo
 * @date 2023-01-03
 */
public interface XxlJobLogMapper extends BaseMapperX<XxlJobLog>
{

    /**
     * 查询定时任务日志列表-分页
     *
     * @param xxlJobLog 定时任务日志
     * @return 定时任务日志集合
     */
    IPage<XxlJobLog> selectXxlJobLogList(Page page,@Param("query") XxlJobLog xxlJobLog);
    /**
     * 查询定时任务日志列表
     *
     * @param xxlJobLog 定时任务日志
     * @return 定时任务日志集合
     */
    List<XxlJobLog> selectXxlJobLogList(@Param("query") XxlJobLog xxlJobLog);

    /**
     * 批量删除定时任务日志
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteXxlJobLogByIds(Long[] ids);

    List<Long> findFailJobLogIds(@Param("pagesize") int pagesize);

    int updateAlarmStatus(@Param("logId") long logId,
                          @Param("oldAlarmStatus") int oldAlarmStatus,
                          @Param("newAlarmStatus") int newAlarmStatus);

    XxlJobLog load(@Param("id") long id);

    int updateTriggerInfo(XxlJobLog xxlJobLog);

    List<Long> findLostJobIds(@Param("losedTime") Date losedTime);

    int updateHandleInfo(XxlJobLog xxlJobLog);

    Map<String, Object> findLogReport(@Param("from") Date from,
                                      @Param("to") Date to);

    List<Long> findClearLogIds(@Param("jobGroup") int jobGroup,
                               @Param("jobId") int jobId,
                               @Param("clearBeforeTime") Date clearBeforeTime,
                               @Param("clearBeforeNum") int clearBeforeNum,
                               @Param("pagesize") int pagesize);


    int clearLog(@Param("logIds") List<Long> logIds);


    long save(XxlJobLog xxlJobLog);


}
