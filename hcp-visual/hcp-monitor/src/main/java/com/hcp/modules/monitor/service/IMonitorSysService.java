package com.hcp.modules.monitor.service;

import java.util.List;

import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.modules.monitor.domain.MonitorSys;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 服务器管理Service接口
 *
 * @author vctgo
 * @date 2022-04-26
 */
public interface IMonitorSysService
{
    /**
     * 查询服务器管理
     *
     * @param id 服务器管理主键
     * @return 服务器管理
     */
    MonitorSys selectMonitorSysById(Long id);

    /**
     * 查询服务器管理列表-分页
     *
     * @param monitorSys 服务器管理
     * @return 服务器管理集合
     */
    IPage<MonitorSys> selectMonitorSysPage(MonitorSys monitorSys);
    /**
     * 查询服务器管理列表
     *
     * @param monitorSys 服务器管理
     * @return 服务器管理集合
     */
    List<MonitorSys> selectMonitorSysList(MonitorSys monitorSys);

    /**
     * 新增服务器管理
     *
     * @param monitorSys 服务器管理
     * @return 结果
     */
    int insertMonitorSys(MonitorSys monitorSys);

    /**
     * 修改服务器管理
     *
     * @param monitorSys 服务器管理
     * @return 结果
     */
    int updateMonitorSys(MonitorSys monitorSys);

    /**
     * 批量删除服务器管理
     *
     * @param ids 需要删除的服务器管理主键集合
     * @return 结果
     */
    int deleteMonitorSysByIds(Long[] ids);

    /**
     * 删除服务器管理信息
     *
     * @param id 服务器管理主键
     * @return 结果
     */
    int deleteMonitorSysById(Long id);

    /**
     * 测试连接结果
     *@param monitorSys 缓存管理
     * @return 结果
     */
    AjaxResult testLink(MonitorSys monitorSys);
}
