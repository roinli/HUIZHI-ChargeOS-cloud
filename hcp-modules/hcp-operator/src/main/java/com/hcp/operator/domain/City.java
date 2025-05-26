package com.hcp.operator.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 省市管理对象 c_city
 *
 * @author hcp
 * @date 2024-08-06
 */
@Data
@TableName("c_city")
public class City extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 城市代码 */
    @TableId(value = "Id")
    @Excel(name = "城市代码")
    private Long Id;

    /** 城市名称 */
    @Excel(name = "城市名称")
    private String Name;

    /** 省级代码 */
    @Excel(name = "省级代码")
    private Long Pid;


}
