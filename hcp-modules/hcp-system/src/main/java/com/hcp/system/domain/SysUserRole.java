package com.hcp.system.domain;

import lombok.Data;

/**
 * 用户和角色关联 sys_user_role
 *
 * @author vctgo
 */
@Data
public class SysUserRole
{
    /** 用户ID */
    private Long userId;

    /** 角色ID */
    private Long roleId;

    /** 租户ID */
    private Long tenantId;
}
