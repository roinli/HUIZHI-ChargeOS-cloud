
package com.jingli.admin.service.impl;

import com.google.common.collect.Lists;
import com.jingli.admin.mapper.RoleMapper;
import com.jingli.admin.mapper.UserRoleMapper;
import com.jingli.admin.model.RolePO;
import com.jingli.admin.model.UserRolePO;
import com.jingli.admin.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName RoleServiceImpl
 * @Description: 实现类
 * @Author yangbin
 * @Version V1.0
 **/
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleCacheService roleCacheService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 查询所有角色信息
     *
     * @return
     */
    @Override
    public List<RolePO> getAllRole() {
        // 缓存取
        List<RolePO> cache = roleCacheService.getAllRoleFromCache();

        if (CollectionUtils.isNotEmpty(cache)) {
            return cache;
        }

        // 数据库取
        List<RolePO> rolePos = roleMapper.selectList(null);

        if (CollectionUtils.isNotEmpty(rolePos)) {
            roleCacheService.addAllRoleCache(rolePos);
        }
        return rolePos;
    }


    @Override
    public List<RolePO> getRoleByUserId(Integer userId) {
        // 所有角色
        List<RolePO> allRole = getAllRole();
        if (CollectionUtils.isEmpty(allRole)) {
            return Lists.newArrayList();
        }

        // 缓存取
        List<RolePO> roleByUserIdFromCache = roleCacheService.getRoleByUserIdFromCache(userId);

        if (CollectionUtils.isNotEmpty(roleByUserIdFromCache)) {
            return roleByUserIdFromCache;
        }
        // 数据库取
        Map<String, Object> param = new HashMap<>();
        param.put("user_id", userId);
        List<UserRolePO> userRolePOS = userRoleMapper.selectByMap(param);

        if (CollectionUtils.isNotEmpty(userRolePOS)) {
            List<Integer> userRoleCodes = userRolePOS.stream().map(UserRolePO::getRoleCode).collect(Collectors.toList());

            List<RolePO> userRoles = allRole.stream().filter(role -> userRoleCodes.contains(role.getRoleCode())).collect(Collectors.toList());

            // 新增缓存
            roleCacheService.addUserRoleCache(userId, userRoles);

            return userRoles;
        }

        return Lists.newArrayList();
    }

}