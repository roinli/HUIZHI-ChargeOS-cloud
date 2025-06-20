package com.hcp.operator.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 运营商信息对象 hlht_operator_info
 *
 * @author hcp
 * @date 2024-08-11
 */
@Data
@TableName("hlht_operator_info")
public class HlhtOperatorInfo extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 组织机构代码 */
    @TableId
    private String operatorId;

    /** 运营商统一社会
信用代码 */
    @Excel(name = "运营商统一社会信用代码")
    private String operatorUcsId;

    /** 运营商全称 */
    @Excel(name = "运营商全称")
    private String operatorName;

    /** 运营商客服电话 */
    @Excel(name = "运营商客服电话")
    private String operatorTel1;

    /** 运营商客服电话 */
    @Excel(name = "运营商客服电话")
    private String operatorTel2;

    /** 运营商注册地址 */
    @Excel(name = "运营商注册地址")
    private String operatorRegAddress;

    /** 备注信息 */
    @Excel(name = "备注信息")
    private String operatorNote;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 是否推送0未推送1推送 */
    @Excel(name = "是否推送0未推送1推送")
    private String isPush;

    @TableField
    private String startTime;

    @TableField
    private String endTime;



}
