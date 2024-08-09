package com.jingli.admin.service.impl;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.jingli.admin.constant.CacheConstant;
import com.jingli.admin.model.MenuPO;
import com.jingli.admin.util.StringUtils;
import com.jingli.common.redis.service.RedisService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.alibaba.fastjson2.JSONWriter.Feature.LargeObject;

/**
 * DESCRIPTION :
 *
 * @author ducf
 * @create 2020-01-10 21:56
 */
@Component
public class MenuCacheService {

    @Autowired
    private RedisService redisService;

    /**
     * 根据角色code 获取所有角色的菜单和按钮
     *
     * @param roleCode
     * @return
     */
    public List<MenuPO> getMenuByRuleCodeFromCache(Integer roleCode) {
        String s = redisService.getCacheObject(CacheConstant.Menu.PREFIX_ROLE + roleCode);
        if (StringUtils.isBlank(s)) {
            return Lists.newArrayList();
        }
        return JSON.parseArray(s, MenuPO.class).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 添加角色 的按钮和菜单
     *
     * @param roleCode
     * @return
     */
    public void addMenuByRuleCodeFromCache(Integer roleCode, List<MenuPO> menus) {
        if (Objects.isNull(roleCode) || CollectionUtils.isEmpty(menus)) {
            return;
        }
        redisService.setCacheObject(CacheConstant.Menu.PREFIX_ROLE + roleCode, JSON.toJSONString(menus));
    }
    /**
     * 缓存用户的菜单权限
     *
     * @param userId
     * @param menus
     */
    public void addMenuByUserIdFromCache(Integer userId, List<MenuPO> menus) {
        if (Objects.isNull(userId) || CollectionUtils.isEmpty(menus)) {
            return;
        }
        redisService.setCacheObject(CacheConstant.Menu.PREFIX_MENU_USER + userId, JSON.toJSONString(menus));
    }

    /**
     * 获取用户的菜单权限
     *
     * @param userId
     * @return
     */
    public List<MenuPO> getMenuByUserIdFromCache(Integer userId) {
        if (Objects.isNull(userId)) {
            return Lists.newArrayList();
        }

        String s = redisService.getCacheObject(CacheConstant.Menu.PREFIX_MENU_USER + userId);
        return StringUtils.isBlank(s) ? Lists.newArrayList() : JSON.parseArray(s, MenuPO.class).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
    /**
     * 缓存所有的菜单和按钮
     *
     * @param menuPOS
     */
    public void addAllMenuAndBtnCache(List<MenuPO> menuPOS) {
        if (CollectionUtils.isEmpty(menuPOS)) {
            return;
        }
        redisService.setCacheObject(CacheConstant.Menu.ALL, JSON.toJSONString(menuPOS, LargeObject));
    }

    /**
     * 获取所有的菜单和按钮缓存
     *
     * @return
     */
    public List<MenuPO> getAllMenuAndBtnFromCahce() {
        String s = redisService.getCacheObject(CacheConstant.Menu.ALL);
        if (StringUtils.isBlank(s)) {
            return Lists.newArrayList();
        }
        return JSON.parseArray(s, MenuPO.class).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

}
