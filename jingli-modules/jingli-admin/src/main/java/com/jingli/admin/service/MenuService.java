
package com.jingli.admin.service;

import com.jingli.admin.model.MenuPO;

import java.util.List;


/**
 * @ClassName Menu
 * @Description:
 * @Author yangbin
 * @Version V1.0
 **/
public interface MenuService {

    /**
     * 获取用户的所有菜单和按钮权限
     *
     * @param userId
     * @return
     */
    List getAllPermissionMenu(Integer userId);


    /**
     * 获取所有菜单,不区分菜单和按钮
     *
     * @return
     */
    List<MenuPO> getAllMenuAndBtn();

    /**
     * 获取角色拥有的菜单Code
     *
     * @return
     */
    List<Integer> getMenuAndBtnByRoleCode(Integer roleCode);


    /**
     * 获取角色的所有菜单
     *
     * @param roleCodes
     * @return
     */
    List<MenuPO> getMenuAndBtnByRoleCodes(List<Integer> roleCodes);

}