package com.hcp.modules.monitor.controller;

import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.hcp.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hcp.common.log.annotation.Log;
import com.hcp.common.log.enums.BusinessType;
import com.hcp.common.security.annotation.RequiresPermissions;
import com.hcp.modules.monitor.domain.MonitorCache;
import com.hcp.modules.monitor.service.IMonitorCacheService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 缓存管理Controller
 *
 * @author vctgo
 * @date 2022-04-27
 */
@RestController
@RequestMapping("/cache")
public class MonitorCacheController extends BaseController
{
    @Autowired
    private IMonitorCacheService monitorCacheService;

    /**
     * 查询缓存管理列表
     */
    @RequiresPermissions("monitor:cache:list")
    @GetMapping("/list")
    public AjaxResult list(MonitorCache monitorCache)
    {
        IPage<MonitorCache> list = monitorCacheService.selectMonitorCachePage(monitorCache);
        return AjaxResult.success(list);
    }

    /**
     * 导出缓存管理列表
     */
    @RequiresPermissions("monitor:cache:export")
    @Log(title = "缓存管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MonitorCache monitorCache)
    {
        List<MonitorCache> list = monitorCacheService.selectMonitorCacheList(monitorCache);
        ExcelUtil<MonitorCache> util = new ExcelUtil<MonitorCache>(MonitorCache.class);
        util.exportExcel(response, list, "缓存管理数据");
    }

    /**
     * 获取缓存管理详细信息
     */
    @RequiresPermissions("monitor:cache:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(monitorCacheService.selectMonitorCacheById(id));
    }

    /**
     * 新增缓存管理
     */
    @RequiresPermissions("monitor:cache:add")
    @Log(title = "缓存管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MonitorCache monitorCache)
    {
        return toAjax(monitorCacheService.insertMonitorCache(monitorCache));
    }

    /**
     * 修改缓存管理
     */
    @RequiresPermissions("monitor:cache:edit")
    @Log(title = "缓存管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MonitorCache monitorCache)
    {
        return toAjax(monitorCacheService.updateMonitorCache(monitorCache));
    }

    /**
     * 删除缓存管理
     */
    @RequiresPermissions("monitor:cache:remove")
    @Log(title = "缓存管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(monitorCacheService.deleteMonitorCacheByIds(ids));
    }


    /**
     * 测试链接
     */
    @PostMapping("/testlink")
    public AjaxResult testlink(@RequestBody MonitorCache monitorCache)
    {
        return monitorCacheService.testLink(monitorCache);
    }

    @GetMapping("/info")
    public AjaxResult getInfo(MonitorCache monitorCache)
    {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(monitorCache.getIp());
        redisStandaloneConfiguration.setDatabase(monitorCache.getDatabaseNum());
        redisStandaloneConfiguration.setPort(monitorCache.getIpPort());
        redisStandaloneConfiguration.setPassword(monitorCache.getUserPassword());

        LettuceClientConfiguration.LettuceClientConfigurationBuilder lettuceClientConfigurationBuilder = LettuceClientConfiguration.builder();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration,
                lettuceClientConfigurationBuilder.build());
        factory.afterPropertiesSet();
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.afterPropertiesSet();
        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info());
        Properties commandStats = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info("commandstats"));
        Object dbSize = redisTemplate.execute((RedisCallback<Object>) connection -> connection.dbSize());
        factory.resetConnection();
        Map<String, Object> result = new HashMap<>(3);
        result.put("info", info);
        result.put("dbSize", dbSize);

        List<Map<String, String>> pieList = new ArrayList<>();
        commandStats.stringPropertyNames().forEach(key -> {
            Map<String, String> data = new HashMap<>(2);
            String property = commandStats.getProperty(key);
            data.put("name", StringUtils.removeStart(key, "cmdstat_"));
            data.put("value", StringUtils.substringBetween(property, "calls=", ",usec"));
            pieList.add(data);
        });
        result.put("commandStats", pieList);
        return AjaxResult.success(result);
    }
}
