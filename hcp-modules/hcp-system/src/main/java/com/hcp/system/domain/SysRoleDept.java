package com.hcp.system.domain;

import lombok.Data;

/**
 * 角色和部门关联 sys_role_dept
 *
 * @author vctgo
 */
@Data
public class SysRoleDept
{
    /** 角色ID */
    private Long roleId;

    /** 部门ID */
    private Long deptId;

    /** 租户ID */
    private Long tenantId;

}
