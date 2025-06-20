package com.hcp.modules.monitor.mapper;

import java.util.List;
import com.hcp.modules.monitor.domain.MonitorCache;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 缓存管理Mapper接口
 *
 * @author vctgo
 * @date 2022-04-27
 */
public interface MonitorCacheMapper extends BaseMapperX<MonitorCache>
{

    /**
     * 查询缓存管理列表-分页
     *
     * @param monitorCache 缓存管理
     * @return 缓存管理集合
     */
    IPage<MonitorCache> selectMonitorCacheList(Page page,@Param("query") MonitorCache monitorCache);
    /**
     * 查询缓存管理列表
     *
     * @param monitorCache 缓存管理
     * @return 缓存管理集合
     */
    List<MonitorCache> selectMonitorCacheList(@Param("query") MonitorCache monitorCache);

    /**
     * 批量删除缓存管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteMonitorCacheByIds(Long[] ids);
}
