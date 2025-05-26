package com.hcp.operator.service;

import java.util.List;
import com.hcp.system.api.domain.ChargingStation;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 站点Service接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface IChargingStationService
{
    /**
     * 查询站点
     *
     * @param stationId 站点主键
     * @return 站点
     */
    ChargingStation selectChargingStationByStationId(Long stationId);

    /**
     * 查询站点列表-分页
     *
     * @param chargingStation 站点
     * @return 站点集合
     */
    IPage<ChargingStation> selectChargingStationPage(ChargingStation chargingStation);
    /**
     * 查询站点列表
     *
     * @param chargingStation 站点
     * @return 站点集合
     */
    List<ChargingStation> selectChargingStationList(ChargingStation chargingStation);

    /**
     * 新增站点
     *
     * @param chargingStation 站点
     * @return 结果
     */
    int insertChargingStation(ChargingStation chargingStation);

    /**
     * 修改站点
     *
     * @param chargingStation 站点
     * @return 结果
     */
    int updateChargingStation(ChargingStation chargingStation);

    /**
     * 批量删除站点
     *
     * @param stationIds 需要删除的站点主键集合
     * @return 结果
     */
    int deleteChargingStationByStationIds(Long[] stationIds);

    /**
     * 删除站点信息
     *
     * @param stationId 站点主键
     * @return 结果
     */
    int deleteChargingStationByStationId(Long stationId);
}
