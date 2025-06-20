package com.hcp.system.api.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 用户余额对象 c_menber_balance
 *
 * @author hcp
 * @date 2024-08-06
 */
@Data
@TableName("c_menber_balance")
public class MenberBalance extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long memberId;

    /** 账户余额 */
    @Excel(name = "账户余额")
    private BigDecimal amount;

    /** 乐观锁 */
    @Excel(name = "乐观锁")
    private Long version;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;



}
