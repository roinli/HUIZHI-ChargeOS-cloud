package com.hcp.modules.monitor.service;

import java.util.List;

import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.modules.monitor.domain.MonitorCache;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 缓存管理Service接口
 *
 * @author vctgo
 * @date 2022-04-27
 */
public interface IMonitorCacheService
{
    /**
     * 查询缓存管理
     *
     * @param id 缓存管理主键
     * @return 缓存管理
     */
    MonitorCache selectMonitorCacheById(Long id);

    /**
     * 查询缓存管理列表-分页
     *
     * @param monitorCache 缓存管理
     * @return 缓存管理集合
     */
    IPage<MonitorCache> selectMonitorCachePage(MonitorCache monitorCache);
    /**
     * 查询缓存管理列表
     *
     * @param monitorCache 缓存管理
     * @return 缓存管理集合
     */
    List<MonitorCache> selectMonitorCacheList(MonitorCache monitorCache);

    /**
     * 新增缓存管理
     *
     * @param monitorCache 缓存管理
     * @return 结果
     */
    int insertMonitorCache(MonitorCache monitorCache);

    /**
     * 修改缓存管理
     *
     * @param monitorCache 缓存管理
     * @return 结果
     */
    int updateMonitorCache(MonitorCache monitorCache);

    /**
     * 批量删除缓存管理
     *
     * @param ids 需要删除的缓存管理主键集合
     * @return 结果
     */
    int deleteMonitorCacheByIds(Long[] ids);

    /**
     * 删除缓存管理信息
     *
     * @param id 缓存管理主键
     * @return 结果
     */
    int deleteMonitorCacheById(Long id);

    /**
     * 测试连接结果
     *@param monitorCache 缓存管理
     * @return 结果
     */
    AjaxResult testLink(MonitorCache monitorCache);
}
