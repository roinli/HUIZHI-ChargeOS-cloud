package com.hcp.job.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 报告对象 xxl_job_log_report
 *
 * @author vctgo
 * @date 2023-01-03
 */
@Data
@TableName("xxl_job_log_report")
public class XxlJobLogReport extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId
    private Long id;

    /** 调度-时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "调度-时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date triggerDay;

    /** 运行中-日志数量 */
    @Excel(name = "运行中-日志数量")
    private Integer runningCount;

    /** 执行成功-日志数量 */
    @Excel(name = "执行成功-日志数量")
    private Integer sucCount;

    /** 执行失败-日志数量 */
    @Excel(name = "执行失败-日志数量")
    private Integer failCount;



}
