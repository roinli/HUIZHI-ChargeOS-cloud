package com.hcp.operator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * 收费规则对象 c_rule
 *
 * @author hcp
 * @date 2024-08-06
 */
@Data
@TableName("c_rule")
public class Rule extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 规则名称 */
    @Excel(name = "规则名称")
    private String changeName;

    /** 充值类型 */
    @Excel(name = "充值类型")
    private String chargeType;

    /** 规则类型 */
    @Excel(name = "规则类型")
    private String ruleType;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 默认规则 */
    @Excel(name = "默认规则")
    private Integer isGive;

    /** 平台默认规则 1:是 0:否 */
    @Excel(name = "平台默认规则 1:是 0:否")
    private Integer isSysGive;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private Long deviceType;

    /** 最大充电时长 */
    private Long maxHour;

    @TableField(exist = false)
    private String userName;


    @Valid
    @ApiModelProperty("价格列表")
    @TableField(exist = false)
    private List<PriceVo> priceList;

}
