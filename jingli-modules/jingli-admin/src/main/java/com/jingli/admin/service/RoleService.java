
package com.jingli.admin.service;

import com.jingli.admin.model.RolePO;
import java.util.List;


public interface RoleService {

    /**
     * 获取所有角色
     *
     * @return
     */
    List<RolePO> getAllRole();

    /**
     * 获取用户的所有角色
     *
     * @return
     */
    List<RolePO> getRoleByUserId(Integer userId);
}