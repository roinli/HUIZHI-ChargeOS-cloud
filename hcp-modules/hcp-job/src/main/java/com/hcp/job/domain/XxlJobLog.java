package com.hcp.job.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * 定时任务日志对象 xxl_job_log
 *
 * @author vctgo
 * @date 2023-01-03
 */
@Data
@TableName("xxl_job_log")
public class XxlJobLog extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId
    private Long id;

    /** 执行器主键ID */
    @Excel(name = "执行器主键ID")
    private Integer jobGroup;

    /** 任务，主键ID */
    @Excel(name = "任务，主键ID")
    private Integer jobId;

    /** 执行器地址，本次执行的地址 */
    @Excel(name = "执行器地址，本次执行的地址")
    private String executorAddress;

    /** 执行器任务handler */
    @Excel(name = "执行器任务handler")
    private String executorHandler;

    /** 执行器任务参数 */
    @Excel(name = "执行器任务参数")
    private String executorParam;

    /** 执行器任务分片参数，格式如 1/2 */
    @Excel(name = "执行器任务分片参数，格式如 1/2")
    private String executorShardingParam;

    /** 失败重试次数 */
    @Excel(name = "失败重试次数")
    private Integer executorFailRetryCount;

    /** 调度-时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "调度-时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date triggerTime;

    /** 调度-结果 */
    @Excel(name = "调度-结果")
    private int triggerCode;

    /** 调度-日志 */
    @Excel(name = "调度-日志")
    private String triggerMsg;

    /** 执行-时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "执行-时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date handleTime;

    /** 执行-状态 */
    @Excel(name = "执行-状态")
    private int handleCode;

    /** 执行-日志 */
    @Excel(name = "执行-日志")
    private String handleMsg;

    /** 告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败 */
    @Excel(name = "告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败")
    private Long alarmStatus;
    @Transient
    private long logDateTim;
    @Transient
    private int fromLineNum;
    @Transient
    private String logStatus;
    /** 调度时间开始 */
    @Transient
    private String triggerTimeStart;
    /** 调度时间结束 */
    @Transient
    private String triggerTimeEnd;
    /** 清理日志类型 */
    @Transient
    private String type;
}
