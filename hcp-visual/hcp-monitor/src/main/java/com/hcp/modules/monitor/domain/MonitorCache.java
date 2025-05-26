package com.hcp.modules.monitor.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import lombok.Data;
import com.hcp.common.core.web.domain.TenantEntity;

/**
 * 缓存管理对象 monitor_cache
 *
 * @author vctgo
 * @date 2022-04-27
 */
@Data
@TableName("monitor_cache")
public class MonitorCache extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId
    private Long id;

    /** 缓存应用名称 */
    @Excel(name = "缓存应用名称")
    private String cacheName;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ip;

    /** 缓存库号 */
    @Excel(name = "缓存库号")
    private Integer databaseNum;

    /** 用户密码 */
    @Excel(name = "用户密码")
    private String userPassword;

    /** 系统端口 */
    @Excel(name = "系统端口")
    private Integer ipPort;

    /** 公钥地址 */
    private String rsaAddr;

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
