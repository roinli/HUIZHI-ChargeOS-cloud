package com.hcp.operator.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.operator.domain.PileTotalResult;
import com.hcp.operator.domain.QueryChargePileVo;
import com.hcp.system.api.domain.ChargingPile;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hcp.system.api.domain.SysDept;
import com.hcp.system.api.domain.vo.ChargingPileVO;
import com.hcp.system.api.domain.vo.PlotDetailVo;
import com.hcp.system.api.domain.vo.PlotInfoReqVO;
import com.hcp.system.api.domain.vo.PlotVO;

/**
 * 充电桩Service接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface IChargingPileService
{
    /**
     * 查询充电桩
     *
     * @param pileId 充电桩主键
     * @return 充电桩
     */
    ChargingPile selectChargingPileByPileId(String pileId);

    /**
     * 查询充电桩列表-分页
     *
     * @param chargingPile 充电桩
     * @return 充电桩集合
     */
    IPage<ChargingPile> selectChargingPilePage(ChargingPile chargingPile);
    /**
     * 查询充电桩列表
     *
     * @param chargingPile 充电桩
     * @return 充电桩集合
     */
    List<ChargingPile> selectChargingPileList(ChargingPile chargingPile);

    /**
     * 新增充电桩
     *
     * @param chargingPile 充电桩
     * @return 结果
     */
    int insertChargingPile(ChargingPile chargingPile);

    /**
     * 修改充电桩
     *
     * @param chargingPile 充电桩
     * @return 结果
     */
    int updateChargingPile(ChargingPile chargingPile);

    /**
     * 批量删除充电桩
     *
     * @param pileIds 需要删除的充电桩主键集合
     * @return 结果
     */
    int deleteChargingPileByPileIds(String[] pileIds);

    /**
     * 删除充电桩信息
     *
     * @param pileId 充电桩主键
     * @return 结果
     */
    int deleteChargingPileByPileId(String pileId);

    IPage<PileTotalResult> getChargeTotalPage(QueryChargePileVo vo);

    List<PileTotalResult> getChargeTotal(QueryChargePileVo vo);

    ChargingPile getById(String pileId);

    List<PlotVO> getPlotInfo(PlotInfoReqVO plotInfoReqVO);

    ChargingPileVO queryChargingPileData(String pileId);

    PlotDetailVo plotDetail(String plotId, String deviceType);

    Page<PlotVO> getPlotInfoPage(PlotInfoReqVO plotInfoReqVO);
}
