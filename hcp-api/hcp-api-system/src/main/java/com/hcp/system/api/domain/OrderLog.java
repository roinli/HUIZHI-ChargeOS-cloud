package com.hcp.system.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 订单操作日志对象 c_order_log
 *
 * @author hcp
 * @date 2024-08-10
 */
@Data
@TableName("c_order_log")
public class OrderLog extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单日志id */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 主流程 1：订单主流程  0：订单日志 */
    @Excel(name = "主流程 1：订单主流程  0：订单日志")
    private Long mainProcess;

    /** 简要描述,订单流程中使用 */
    @Excel(name = "简要描述,订单流程中使用")
    private String briefInfo;

    /** 日志内容 */
    @Excel(name = "日志内容")
    private String logContent;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNumber;



}
