package com.jingli.inter.service.impl;

import com.jingli.inter.domain.PilePortVO;
import com.jingli.inter.entity.ChargingPort;
import com.jingli.inter.mapper.ChargingPortMapper;
import com.jingli.inter.service.ChargingPortService;
import com.jingli.inter.vo.req.PlotInfoReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 充电桩端口表;(c_charging_port)表服务实现类
 */
@Service
public class ChargingPortServiceImpl implements ChargingPortService {
    @Autowired
    private ChargingPortMapper chargingPortMapper;

    @Override
    public List<ChargingPort> queryChargingPilePortData(String key) {
        return chargingPortMapper.queryChargingPilePortData(key);
    }

    @Override
    public ChargingPort querySwitchPortByPortId(String orderPortId) {
        return chargingPortMapper.querySwitchPortByPortId(orderPortId);
    }
    @Override
    public List<PilePortVO> queryPortInfoVo(PlotInfoReqVO vo) {
        return chargingPortMapper.queryPortInfoVo(vo);
    }
}