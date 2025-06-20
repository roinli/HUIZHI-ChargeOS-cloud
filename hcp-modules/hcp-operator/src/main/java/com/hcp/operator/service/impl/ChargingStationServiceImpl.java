package com.hcp.operator.service.impl;

import java.util.List;

import com.hcp.common.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.mapper.ChargingStationMapper;
import com.hcp.system.api.domain.ChargingStation;
import com.hcp.operator.service.IChargingStationService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 站点Service业务层处理
 *
 * @author hcp
 * @date 2024-08-06
 */
@Service
public class ChargingStationServiceImpl implements IChargingStationService
{
    @Autowired
    private ChargingStationMapper chargingStationMapper;

    /**
     * 查询站点
     *
     * @param stationId 站点主键
     * @return 站点
     */
    @Override
    public ChargingStation selectChargingStationByStationId(Long stationId)
    {
        return chargingStationMapper.selectById(stationId);
    }

    /**
     * 查询站点列表-分页
     *
     * @param chargingStation 站点
     * @return 站点
     */
    @Override
    public IPage<ChargingStation> selectChargingStationPage(ChargingStation chargingStation)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return chargingStationMapper.selectChargingStationList(mpPage,chargingStation);
    }
    /**
     * 查询站点列表
     *
     * @param chargingStation 站点
     * @return 站点
     */
    @Override
    public List<ChargingStation> selectChargingStationList(ChargingStation chargingStation)
    {
        return chargingStationMapper.selectChargingStationList(chargingStation);
    }

    /**
     * 新增站点
     *
     * @param chargingStation 站点
     * @return 结果
     */

    @Override
    public int insertChargingStation(ChargingStation chargingStation)
    {
        chargingStation.setUserId(SecurityContextHolder.getUserId());
        return chargingStationMapper.insert(chargingStation);
    }

    /**
     * 修改站点
     *
     * @param chargingStation 站点
     * @return 结果
     */
    @Override
    public int updateChargingStation(ChargingStation chargingStation)
    {
        chargingStation.setUserId(SecurityContextHolder.getUserId());
        return chargingStationMapper.updateById(chargingStation);
    }

    /**
     * 批量删除站点
     *
     * @param stationIds 需要删除的站点主键
     * @return 结果
     */
    @Override
    public int deleteChargingStationByStationIds(Long[] stationIds)
    {
        return chargingStationMapper.deleteChargingStationByStationIds(stationIds);
    }

    /**
     * 删除站点信息
     *
     * @param stationId 站点主键
     * @return 结果
     */
    @Override
    public int deleteChargingStationByStationId(Long stationId)
    {
        return chargingStationMapper.deleteById(stationId);
    }
}
