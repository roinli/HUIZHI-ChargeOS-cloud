package com.hcp.system.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import com.hcp.system.api.domain.SysUser;
import lombok.Data;

/**
 * 用户信息
 *
 * @author vctgo
 */
@Data
public class LoginUser implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private String token;
    /**
     * 用户名id
     */
    private Long userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;
    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 角色列表
     */
    private Set<String> roles;

    /**
     * 用户信息
     */
    private SysUser sysUser;

    /**
     * 租户租赁截止日期--dhr
     */
    private Date tenantEndDate;

    /**
     * 租户状态
     */
    private Integer tenantStatus;

}
