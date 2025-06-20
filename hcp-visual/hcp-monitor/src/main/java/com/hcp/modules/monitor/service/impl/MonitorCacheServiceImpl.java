package com.hcp.modules.monitor.service.impl;

import java.time.Duration;
import java.util.List;

import com.hcp.common.core.utils.StringUtils;
import com.hcp.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.hcp.modules.monitor.mapper.MonitorCacheMapper;
import com.hcp.modules.monitor.domain.MonitorCache;
import com.hcp.modules.monitor.service.IMonitorCacheService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 缓存管理Service业务层处理
 *
 * @author vctgo
 * @date 2022-04-27
 */
@Service
public class MonitorCacheServiceImpl implements IMonitorCacheService
{
    @Autowired
    private MonitorCacheMapper monitorCacheMapper;



    /**
     * 查询缓存管理
     *
     * @param id 缓存管理主键
     * @return 缓存管理
     */
    @Override
    public MonitorCache selectMonitorCacheById(Long id)
    {
        return monitorCacheMapper.selectById(id);
    }

    /**
     * 查询缓存管理列表-分页
     *
     * @param monitorCache 缓存管理
     * @return 缓存管理
     */
    @Override
    public IPage<MonitorCache> selectMonitorCachePage(MonitorCache monitorCache)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return monitorCacheMapper.selectMonitorCacheList(mpPage,monitorCache);
    }
    /**
     * 查询缓存管理列表
     *
     * @param monitorCache 缓存管理
     * @return 缓存管理
     */
    @Override
    public List<MonitorCache> selectMonitorCacheList(MonitorCache monitorCache)
    {
        return monitorCacheMapper.selectMonitorCacheList(monitorCache);
    }

    /**
     * 新增缓存管理
     *
     * @param monitorCache 缓存管理
     * @return 结果
     */

    @Override
    public int insertMonitorCache(MonitorCache monitorCache)
    {
        return monitorCacheMapper.insert(monitorCache);
    }

    /**
     * 修改缓存管理
     *
     * @param monitorCache 缓存管理
     * @return 结果
     */
    @Override
    public int updateMonitorCache(MonitorCache monitorCache)
    {
        return monitorCacheMapper.updateById(monitorCache);
    }

    /**
     * 批量删除缓存管理
     *
     * @param ids 需要删除的缓存管理主键
     * @return 结果
     */
    @Override
    public int deleteMonitorCacheByIds(Long[] ids)
    {
        return monitorCacheMapper.deleteMonitorCacheByIds(ids);
    }

    /**
     * 删除缓存管理信息
     *
     * @param id 缓存管理主键
     * @return 结果
     */
    @Override
    public int deleteMonitorCacheById(Long id)
    {
        return monitorCacheMapper.deleteById(id);
    }

    /**
     * 测试连接结果
     * @param monitorCache 缓存管理
     * @return
     */
    @Override
    public AjaxResult testLink(MonitorCache monitorCache) {
        try {
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
            redisStandaloneConfiguration.setHostName(monitorCache.getIp());
            redisStandaloneConfiguration.setDatabase(monitorCache.getDatabaseNum());
            redisStandaloneConfiguration.setPort(monitorCache.getIpPort());
            redisStandaloneConfiguration.setPassword(monitorCache.getUserPassword());
            LettuceClientConfiguration.LettuceClientConfigurationBuilder lettuceClientConfigurationBuilder = LettuceClientConfiguration.builder()
                    .commandTimeout(Duration.ofMillis(500L));
            LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration,
                    lettuceClientConfigurationBuilder.build());
            factory.afterPropertiesSet();
            RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(factory);
            redisTemplate.afterPropertiesSet();
            Object dbSize = redisTemplate.execute((RedisCallback<Object>) connection -> connection.dbSize());
            factory.resetConnection();
            if (StringUtils.isEmpty(dbSize)){
                return AjaxResult.success("连接失败!","error");
            }
            return AjaxResult.success("连接成功!","success");
        }catch (Exception e){
            return AjaxResult.success("连接失败!","error");
        }
    }
}
