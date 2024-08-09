package com.jingli.inter.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingli.inter.domain.ChargingPileVO;
import com.jingli.inter.domain.NewsDto;
import com.jingli.inter.domain.PlotVO;
import com.jingli.inter.entity.ChargingPile;
import com.jingli.inter.vo.req.PlotInfoReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

 /**
 * 充电桩表;(c_charging_pile)表数据库访问层
 * @author : Abdulla
 * @date : 2023-8-25
 */
@Mapper
public interface ChargingPileMapper extends BaseMapper<ChargingPile> {




     ChargingPileVO queryChargingPileData(String key);

     ChargingPile queryChargingPile(String chargingPileNo);


     String queryConfigByKey(String configKey);

     NewsDto queryNewsByNewsConfigKey(@Param("newsConfigKey") String newsConfigKey,@Param("type") String type);

     List<NewsDto> queryNewsNameList(@Param("type") String type);


     //
     List<PlotVO> queryPlotInfoByVo(PlotInfoReqVO plotInfoReqVO);

 }