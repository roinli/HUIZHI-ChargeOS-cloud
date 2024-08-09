package com.jingli.inter.service;

import com.jingli.inter.domain.PilePortVO;
import com.jingli.inter.entity.ChargingPort;
import com.jingli.inter.vo.req.PlotInfoReqVO;

import java.util.List;

/**
 * 充电桩端口表;(c_charging_port)表服务接口
 */
public interface ChargingPortService{
    List<ChargingPort> queryChargingPilePortData(String key);

    ChargingPort querySwitchPortByPortId(String orderPortId);

    List<PilePortVO> queryPortInfoVo(PlotInfoReqVO vo);
}