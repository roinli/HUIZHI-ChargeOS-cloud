package com.hcp.modules.monitor.mapper;

import java.util.List;
import com.hcp.modules.monitor.domain.MonitorSys;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 服务器管理Mapper接口
 *
 * @author vctgo
 * @date 2022-04-26
 */
public interface MonitorSysMapper extends BaseMapperX<MonitorSys>
{

    /**
     * 查询服务器管理列表-分页
     *
     * @param monitorSys 服务器管理
     * @return 服务器管理集合
     */
    IPage<MonitorSys> selectMonitorSysList(Page page,@Param("query") MonitorSys monitorSys);
    /**
     * 查询服务器管理列表
     *
     * @param monitorSys 服务器管理
     * @return 服务器管理集合
     */
    List<MonitorSys> selectMonitorSysList(@Param("query") MonitorSys monitorSys);

    /**
     * 批量删除服务器管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteMonitorSysByIds(Long[] ids);
}
