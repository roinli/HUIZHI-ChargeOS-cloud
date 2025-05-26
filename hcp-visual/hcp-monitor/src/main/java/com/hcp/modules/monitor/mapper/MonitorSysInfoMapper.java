package com.hcp.modules.monitor.mapper;

import java.util.List;
import com.hcp.modules.monitor.domain.MonitorSysInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 系统监控信息记录Mapper接口
 *
 * @author vctgo
 * @date 2022-04-26
 */
public interface MonitorSysInfoMapper extends BaseMapperX<MonitorSysInfo>
{

    /**
     * 查询系统监控信息记录列表-分页
     *
     * @param monitorSysInfo 系统监控信息记录
     * @return 系统监控信息记录集合
     */
    IPage<MonitorSysInfo> selectMonitorSysInfoList(Page page,@Param("query") MonitorSysInfo monitorSysInfo);
    /**
     * 查询系统监控信息记录列表
     *
     * @param monitorSysInfo 系统监控信息记录
     * @return 系统监控信息记录集合
     */
    List<MonitorSysInfo> selectMonitorSysInfoList(@Param("query") MonitorSysInfo monitorSysInfo);

    /**
     * 批量删除系统监控信息记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteMonitorSysInfoByIds(Long[] ids);


    /**
     * 删除监控信息
     *
     * @param ipid 需要删除的数据id
     * @return 结果
     */
    int deleteByIpid(Long[] ipid);
}
