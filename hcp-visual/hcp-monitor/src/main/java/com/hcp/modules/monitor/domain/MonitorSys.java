package com.hcp.modules.monitor.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import lombok.Data;
import com.hcp.common.core.web.domain.TenantEntity;

/**
 * 服务器管理对象 monitor_sys
 *
 * @author vctgo
 * @date 2022-04-26
 */
@Data
@TableName("monitor_sys")
public class MonitorSys extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId
    private Long id;

    /** 系统名称 */
    @Excel(name = "系统名称")
    private String sysName;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ip;

    /** 用户名 */
    private String userName;

    /** 用户密码 */
    private String userPassword;

    /** 系统端口 */
    private Integer ipPort;

    /** 公钥地址 */
    private String rsaAddr;

    /** 是否在线 0在线 1离线 */
    @Excel(name = "是否在线 0在线 1离线")
    private Integer isOnline;

    @TableField(exist = false)
    /** cpu信息 */
    private String cpuInfo;

    /** 内存信息 */
    @TableField(exist = false)
    private String memInfo;

    /** 硬盘信息 */
    @TableField(exist = false)
    private String diskInfo;

    /** 硬盘信息 */
    @TableField(exist = false)
    private String diskPercent;

    /** cpu核心信息 */
    @TableField(exist = false)
    private String cpuCoreInfo;

    /** 用户手机*/
    @Excel(name = "用户手机")
    private String userPhone;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String userEmail;

    /** 提醒类型 0 手机 1邮箱 */
    @Excel(name = "提醒类型 0 手机 1邮箱")
    private Integer messageType;

}
