
package com.jingli.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.jingli.admin.constant.Constant;
import com.jingli.admin.exception.ServiceException;
import com.jingli.admin.mapper.MenuMapper;
import com.jingli.admin.mapper.RoleMenuMapper;
import com.jingli.admin.model.MenuPO;
import com.jingli.admin.model.RoleMenuPO;
import com.jingli.admin.model.RolePO;
import com.jingli.admin.service.MenuService;
import com.jingli.admin.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName MenuServiceImpl
 * @Description: 菜单实现类
 * @Author yangbin
 * @Version V1.0
 **/
@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    MenuCacheService menuCacheService;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuMapper roleMenuMapper;


    @Override
    public List getAllPermissionMenu(Integer userId) {
        if (Objects.isNull(userId)) {
            return Lists.newArrayList();
        }

        List<MenuPO> menuByUserIdFromCache = menuCacheService.getMenuByUserIdFromCache(userId);
        if (CollectionUtils.isNotEmpty(menuByUserIdFromCache)) {
            return menuByUserIdFromCache;
        }

        List<MenuPO> menus = this.getUserMenuFromDB(userId);

        if (CollectionUtils.isNotEmpty(menus)) {
            menuCacheService.addMenuByUserIdFromCache(userId, menus);
        }

        return menus;
    }

    private List<MenuPO> getUserMenuFromDB(Integer userId) {

        // 获取用户所有的角色
        List<RolePO> roles = roleService.getRoleByUserId(userId);
        if (CollectionUtils.isEmpty(roles)) {
            return Lists.newArrayList();
        }
        List<Integer> roleCodes = roles.stream().map(RolePO::getRoleCode).collect(Collectors.toList());

        List<MenuPO> menuPOS = this.getMenuAndBtnByRoleCodes(roleCodes);
        if (CollectionUtils.isEmpty(menuPOS)) {
            return Lists.newArrayList();
        }

        // 过滤按钮
        List<MenuPO> menuPOList = menuPOS.stream().filter(MenuPO::isMenu).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(menuPOList)) {
            return Lists.newArrayList();
        }
        // 整理为树形结构,并按照 sequence 排序

        // 聚合所有parentCode
        Map<Integer, List<MenuPO>> parentCodeMap = menuPOList.stream().collect(Collectors.groupingBy(MenuPO::getParentCode));

        menuPOList.stream().forEach(child -> {

            List<MenuPO> childs = parentCodeMap.get(child.getMenuCode());
            if (CollectionUtils.isNotEmpty(childs)) {
                childs = childs.stream().sorted(Comparator.comparing(MenuPO::getSequence)).collect(Collectors.toList());
                child.setChildren(childs);
            }

        });

        // 返回所有顶级节点
        List<MenuPO> menus = menuPOList.stream()
                .filter(menu -> menu.getParentCode().equals(Constant.MENT_CODE_TOP))
                .sorted(Comparator.comparing(MenuPO::getSequence))
                .collect(Collectors.toList());

        return menus;
    }

    @Override
    public List<MenuPO> getAllMenuAndBtn() {
        List<MenuPO> allMenuFromCahce = menuCacheService.getAllMenuAndBtnFromCahce();
        if (CollectionUtils.isNotEmpty(allMenuFromCahce)) {
            return allMenuFromCahce;
        }

        List<MenuPO> menuPOS = menuMapper.selectList(new QueryWrapper<>());
        if (CollectionUtils.isNotEmpty(menuPOS)) {

            menuPOS = MenuPO.setParentNames(menuPOS, menuPOS);

            menuCacheService.addAllMenuAndBtnCache(menuPOS);
        }
        return menuPOS;
    }

    @Override
    public List<Integer> getMenuAndBtnByRoleCode(Integer roleCode) {
        if (Objects.isNull(roleCode)) {
            throw new ServiceException("角色code必填");
        }

        List<MenuPO> menuCache = menuCacheService.getMenuByRuleCodeFromCache(roleCode);
        if (CollectionUtils.isNotEmpty(menuCache)) {
            return menuCache.stream().map(MenuPO::getMenuCode).collect(Collectors.toList());
        }

        List<RoleMenuPO> roleMenuPOS = roleMenuMapper.selectList(new QueryWrapper<RoleMenuPO>().eq("role_code", roleCode));
        if (CollectionUtils.isNotEmpty(roleMenuPOS)) {
            // 缓存
            Map<Integer, MenuPO> menuMap = this.getAllMenuAndBtn().stream().collect(Collectors.toMap(MenuPO::getMenuCode, Function.identity()));
            List<MenuPO> menuPOS = roleMenuPOS.stream().map(rm -> menuMap.get(rm.getMenuCode())).collect(Collectors.toList());
            menuCacheService.addMenuByRuleCodeFromCache(roleCode, menuPOS);

            return roleMenuPOS.stream().map(RoleMenuPO::getMenuCode).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }


    @Override
    public List<MenuPO> getMenuAndBtnByRoleCodes(List<Integer> roleCodes) {
        if (CollectionUtils.isEmpty(roleCodes)) {
            return Lists.newArrayList();
        }

        // 获取角色所有的菜单和按钮
        Set<Integer> menuCodes = roleCodes.stream().flatMap(code -> this.getMenuAndBtnByRoleCode(code).stream()).collect(Collectors.toSet());

        List<MenuPO> allMenus = this.getAllMenuAndBtn();
        if (CollectionUtils.isEmpty(allMenus)) {
            return Lists.newArrayList();
        }

        List<MenuPO> menuPOS = allMenus.stream().filter(menu -> menuCodes.contains(menu.getMenuCode())).collect(Collectors.toList());
        return menuPOS;
    }


}