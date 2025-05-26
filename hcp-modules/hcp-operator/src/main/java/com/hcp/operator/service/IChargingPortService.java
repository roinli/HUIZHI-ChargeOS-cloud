package com.hcp.operator.service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.system.api.domain.ChargingPort;
import com.hcp.system.api.domain.vo.PilePortVO;
import com.hcp.system.api.domain.vo.PlotInfoReqVO;

/**
 * 充电桩端口Service接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface IChargingPortService
{
    /**
     * 查询充电桩端口
     *
     * @param portId 充电桩端口主键
     * @return 充电桩端口
     */
    ChargingPort selectChargingPortByPortId(Long portId);

    /**
     * 查询充电桩端口列表-分页
     *
     * @param chargingPort 充电桩端口
     * @return 充电桩端口集合
     */
    IPage<ChargingPort> selectChargingPortPage(ChargingPort chargingPort);
    /**
     * 查询充电桩端口列表
     *
     * @param chargingPort 充电桩端口
     * @return 充电桩端口集合
     */
    List<ChargingPort> selectChargingPortList(ChargingPort chargingPort);

    /**
     * 新增充电桩端口
     *
     * @param chargingPort 充电桩端口
     * @return 结果
     */
    int insertChargingPort(ChargingPort chargingPort);

    /**
     * 修改充电桩端口
     *
     * @param chargingPort 充电桩端口
     * @return 结果
     */
    int updateChargingPort(ChargingPort chargingPort);

    /**
     * 批量删除充电桩端口
     *
     * @param portIds 需要删除的充电桩端口主键集合
     * @return 结果
     */
    int deleteChargingPortByPortIds(Long[] portIds);

    /**
     * 删除充电桩端口信息
     *
     * @param portId 充电桩端口主键
     * @return 结果
     */
    int deleteChargingPortByPortId(Long portId);

    AjaxResult switchPort(Integer id, Integer type);

    /**
     * 更新插枪状态
     * @param pileId 桩Id
     * @param port 接口编号
     * @param gunInsert 插枪状态 1 插枪  拔枪
     */
    void updateGunStatus(String pileId, String port, String gunInsert);

    List<PilePortVO> queryPortInfoVo(PlotInfoReqVO plotInfoReqVO);

    List<ChargingPort> selectPortByPileId(String pileId);
}
