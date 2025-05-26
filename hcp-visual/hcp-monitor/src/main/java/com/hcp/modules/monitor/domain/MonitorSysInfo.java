package com.hcp.modules.monitor.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import lombok.Data;
import com.hcp.common.core.web.domain.TenantEntity;

/**
 * 系统监控信息记录对象 monitor_sys_info
 *
 * @author vctgo
 * @date 2022-04-26
 */
@Data
@TableName("monitor_sys_info")
public class MonitorSysInfo extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId
    private Long id;

    /** IP地址_id */
    @Excel(name = "IP地址ID")
    private Long ipId;

    /** cpu信息 */
    @Excel(name = "cpu信息")
    private String cpuInfo;

    /** 内存信息 */
    @Excel(name = "内存信息")
    private String memInfo;

    /** 硬盘信息 */
    @Excel(name = "硬盘信息")
    private String diskInfo;

    /** 硬盘信息 */
    @Excel(name = "硬盘信息占比")
    private String diskPercent;

    /** cpu核心信息 */
    @Excel(name = "cpu核心信息")
    private String cpuCoreInfo;



}
