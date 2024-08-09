package com.jingli.admin.service.impl;

import com.alibaba.fastjson2.JSON;
import com.jingli.admin.constant.CacheConstant;
import com.jingli.admin.exception.ServiceException;
import com.jingli.admin.model.RolePO;
import com.jingli.common.redis.service.RedisService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.alibaba.fastjson2.JSONWriter.Feature.LargeObject;

/**
 * DESCRIPTION :
 *
 * @author ducf
 * @create 2020-01-10 22:05
 */
@Component
public class RoleCacheService {

    @Autowired
    private RedisService redisService;


    /**
     * 获取所有角色数据
     *
     * @return
     */
    public List<RolePO> getAllRoleFromCache() {
        String json = redisService.getCacheObject(CacheConstant.Role.ALL);
        return Strings.isBlank(json) ? null : JSON.parseArray(json, RolePO.class).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 获取用户的角色数据
     *
     * @param userId
     * @return
     */
    public List<RolePO> getRoleByUserIdFromCache(Integer userId) {
        if (Objects.isNull(userId)) {
            throw new ServiceException("查询用户角色,用户id不能为空");
        }
        String json = redisService.getCacheObject(CacheConstant.Role.PREFIX_USER + userId);
        return Strings.isBlank(json) ? null : JSON.parseArray(json, RolePO.class).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 缓存用户角色
     *
     * @param userId
     * @param roles
     */
    public void addUserRoleCache(Integer userId, List<RolePO> roles) {
        if (Objects.isNull(userId)) {
            throw new ServiceException("新增用户角色,用户id不能为空");
        }
        redisService.setCacheObject(CacheConstant.Role.PREFIX_USER + userId, JSON.toJSONString(roles, LargeObject));
    }

    /**
     * 缓存所有角色
     *
     * @param rolePos
     */
    public void addAllRoleCache(List<RolePO> rolePos) {
        redisService.setCacheObject(CacheConstant.Role.ALL, JSON.toJSONString(rolePos, LargeObject));
    }
}
