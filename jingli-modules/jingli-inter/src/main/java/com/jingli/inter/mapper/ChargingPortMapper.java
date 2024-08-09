package com.jingli.inter.mapper;


import com.jingli.inter.domain.PilePortVO;
import com.jingli.inter.entity.ChargingPort;
import com.jingli.inter.vo.req.PlotInfoReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

 /**
 * 充电桩端口表;(c_charging_port)表数据库访问层
 * @author : Abdulla
 * @date : 2023-8-25
 */
@Mapper
public interface ChargingPortMapper{
     ChargingPort queryById(Integer id);


     List<ChargingPort> queryChargingPilePortData(String key);

     ChargingPort querySwitchPortByPortId(String orderPortId);

     List<PilePortVO> queryPortInfoVo(PlotInfoReqVO vo);
 }