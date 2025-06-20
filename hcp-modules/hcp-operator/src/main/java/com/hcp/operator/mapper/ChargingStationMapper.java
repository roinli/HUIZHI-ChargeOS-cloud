package com.hcp.operator.mapper;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.hcp.system.api.domain.ChargingStation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 站点Mapper接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface ChargingStationMapper extends BaseMapperX<ChargingStation>
{

    /**
     * 查询站点列表-分页
     *
     * @param chargingStation 站点
     * @return 站点集合
     */
    IPage<ChargingStation> selectChargingStationList(Page page,@Param("query") ChargingStation chargingStation);
    /**
     * 查询站点列表
     *
     * @param chargingStation 站点
     * @return 站点集合
     */
    List<ChargingStation> selectChargingStationList(@Param("query") ChargingStation chargingStation);

    /**
     * 批量删除站点
     *
     * @param stationIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteChargingStationByStationIds(Long[] stationIds);


    @InterceptorIgnore(tenantLine = "1")
    int deleteByPrimaryKey(Long id);
    @InterceptorIgnore(tenantLine = "1")
    int insertStation(ChargingStation record);
    @InterceptorIgnore(tenantLine = "1")
    int insertSelective(ChargingStation record);
    @InterceptorIgnore(tenantLine = "1")
    ChargingStation selectByPrimaryKey(Long id);
    @InterceptorIgnore(tenantLine = "1")
    int updateByPrimaryKeySelective(ChargingStation record);
    @InterceptorIgnore(tenantLine = "1")
    int updateByPrimaryKey(ChargingStation record);
}
