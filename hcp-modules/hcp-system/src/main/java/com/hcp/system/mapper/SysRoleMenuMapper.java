package com.hcp.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.hcp.system.domain.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

/**
 * 角色与菜单关联表 数据层
 *
 * @author vctgo
 */
 public interface SysRoleMenuMapper
{
    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
     int checkMenuExistRole(Long menuId);

    /**
     * 通过角色ID删除角色和菜单关联
     *
     * @param roleId 角色ID
     * @return 结果
     */
     int deleteRoleMenuByRoleId(Long roleId);

    /**
     * 通过租户ID删除角色和菜单关联
     *
     * @param tenantId 租户ID
     * @return 结果
     */
    @InterceptorIgnore(tenantLine = "1")
    int deleteRoleMenuByTenantId(Long tenantId);

    /**
     * 批量删除角色菜单关联信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteRoleMenu(Long[] ids);

    /**
     * 批量新增角色菜单信息
     *
     * @param roleMenuList 角色菜单列表
     * @return 结果
     */
     int batchRoleMenu(List<SysRoleMenu> roleMenuList);



    /**
     * 通过租户ID删除角色和菜单关联
     *
     * @param ids 租户ID
     * @return 结果
     */
    @InterceptorIgnore(tenantLine = "1")
    int deleteRoleMenuByTenantIds(Long[] ids);


    /**
     * 通过租户ID和菜单id定向删除角色和菜单关联
     *
     * @param tenantId 租户ID
     * @return 结果
     */
    @InterceptorIgnore(tenantLine = "1")
    int deleteRoleMenuByTenantIdAndPackage(@Param("tenantId") Long tenantId,@Param("menuids") Long[] menuids);


    /**
     * 批量新增角色菜单信息-根据租户套餐改变
     *
     * @param roleMenuList 角色菜单列表
     * @return 结果
     */
    @InterceptorIgnore(tenantLine = "1")
    int batchRoleMenuByPackage(List<SysRoleMenu> roleMenuList);
}
