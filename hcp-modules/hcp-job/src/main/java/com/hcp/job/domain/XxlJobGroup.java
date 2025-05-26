package com.hcp.job.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 定时任务分组对象 xxl_job_group
 *
 * @author vctgo
 * @date 2023-01-01
 */
@Data
@TableName("xxl_job_group")
public class XxlJobGroup extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId
    private Long id;

    /** 执行器AppName */
    @Excel(name = "执行器AppName")
    private String appname;

    /** 执行器名称 */
    @Excel(name = "执行器名称")
    private String title;

    /** 执行器地址类型：0=自动注册、1=手动录入 */
    @Excel(name = "执行器地址类型：0=自动注册、1=手动录入")
    private Integer addressType;

    /** 执行器地址列表，多地址逗号分隔 */
    @Excel(name = "执行器地址列表，多地址逗号分隔")
    private String addressList;

    // registry list
    private List<String> registryList;  // 执行器地址列表(系统注册)
    public List<String> getRegistryList() {
        if (addressList!=null && addressList.trim().length()>0) {
            registryList = new ArrayList<String>(Arrays.asList(addressList.split(",")));
        }
        return registryList;
    }

}
