package com.hcp.demo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import lombok.Data;
import com.hcp.common.core.web.domain.TenantEntity;

/**
 * 测试对象 sys_demo
 *
 * @author vctgo
 * @date 2022-04-11
 */
@Data
@TableName("sys_demo")
public class Demo extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 测试ID */
    @Excel(name = "测试ID")
    @TableId
    private Long demoId;

    /** 测试账号 */
    @Excel(name = "测试账号")
    private String demoName;

    /** 部门ID */
    private Long deptId;


}
