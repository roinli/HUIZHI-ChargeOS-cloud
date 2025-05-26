package com.hcp.job.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 任务对象 xxl_job_info
 *
 * @author vctgo
 * @date 2022-12-31
 */
@Data
@TableName("xxl_job_info")
public class XxlJobInfo extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId
    private Integer id;

    /** 执行器主键ID */
    @Excel(name = "执行器主键ID")
    private Integer jobGroup;

    /** $column.columnComment */
    @Excel(name = "任务描述")
    private String jobDesc;

    /** $column.columnComment */
    @Excel(name = "添加时间")
    private Date addTime;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 报警邮件 */
    @Excel(name = "报警邮件")
    private String alarmEmail;

    /** 调度类型 */
    @Excel(name = "调度类型")
    private String scheduleType;

    /** 调度配置，值含义取决于调度类型 */
    @Excel(name = "调度配置，值含义取决于调度类型")
    private String scheduleConf;

    /** 调度过期策略 */
    @Excel(name = "调度过期策略")
    private String misfireStrategy;

    /** 执行器路由策略 */
    @Excel(name = "执行器路由策略")
    private String executorRouteStrategy;

    /** 执行器任务handler */
    @Excel(name = "执行器任务handler")
    private String executorHandler;

    /** 执行器任务参数 */
    @Excel(name = "执行器任务参数")
    private String executorParam;

    /** 阻塞处理策略 */
    @Excel(name = "阻塞处理策略")
    private String executorBlockStrategy;

    /** 任务执行超时时间，单位秒 */
    @Excel(name = "任务执行超时时间，单位秒")
    private Integer executorTimeout;

    /** 失败重试次数 */
    @Excel(name = "失败重试次数")
    private Integer executorFailRetryCount;

    /** GLUE类型 */
    @Excel(name = "GLUE类型")
    private String glueType;

    /** GLUE源代码 */
    @Excel(name = "GLUE源代码")
    private String glueSource;

    /** GLUE备注 */
    @Excel(name = "GLUE备注")
    private String glueRemark;

    /** GLUE更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "GLUE更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date glueUpdatetime;

    /** 子任务ID，多个逗号分隔 */
    @Excel(name = "子任务ID，多个逗号分隔")
    private String childJobid;

    /** 调度状态：0-停止，1-运行 */
    @Excel(name = "调度状态：0-停止，1-运行")
    private Integer triggerStatus;

    /** 上次调度时间 */
    @Excel(name = "上次调度时间")
    private long triggerLastTime;

    /** 下次调度时间 */
    @Excel(name = "下次调度时间")
    private long triggerNextTime;


    private String childJobId;		// 子任务ID，多个逗号分隔


    public String getChildJobId() {
        return childJobId;
    }

    public void setChildJobId(String childJobId) {
        this.childJobId = childJobId;
    }
}
