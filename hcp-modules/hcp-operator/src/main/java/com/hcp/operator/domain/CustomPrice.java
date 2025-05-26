package com.hcp.operator.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Builder;
import lombok.Data;

/**
 * 价格对象 c_custom_price
 *
 * @author hcp
 * @date 2024-08-06
 */
@Data
@Builder
@TableName("c_custom_price")
public class CustomPrice extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId
    private Long priceId;

    /** 计费类型 */
    @Excel(name = "计费类型")
    private String chargeType;

    /** 时长 */
    private String time;

    /** 电费 */
    @Excel(name = "电费")
    private BigDecimal price;

    /** 每小时 */
    private BigDecimal hours;

    /** 类型 */
    private String type;

    /** 授权数量 */
    private String granternum;

    /** 授权加个 */
    private String granterprice;

    /** 充电桩ID */
    @Excel(name = "充电桩ID")
    private String pileId;

    /** 规则ID */
    @Excel(name = "规则ID")
    private String ruleId;

    /** 删除标记 */
    private Long delFlag;

    /** 服务费用 */
    @Excel(name = "服务费用")
    private BigDecimal servicePrice;

    /** 最小瓦数 */
    private Long minWatt;

    /** 最大瓦数 */
    private Long maxWatt;

    /** 电费类型 */
    @Excel(name = "电费类型")
    private Long priceType;

    /** 时间范围 */
    @Excel(name = "时间范围")
    private String rangTime;



}
