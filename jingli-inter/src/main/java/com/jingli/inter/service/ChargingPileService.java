package com.jingli.inter.service;

import com.jingli.inter.domain.ChargingPileVO;
import com.jingli.inter.domain.NewsDto;
import com.jingli.inter.domain.PlotVO;
import com.jingli.inter.entity.ChargingPile;
import com.jingli.inter.vo.req.PlotInfoReqVO;

import java.util.List;

/**
 * 充电桩表;(c_charging_pile)表服务接口
 */
public interface ChargingPileService{

    List<PlotVO> getPlotInfo(PlotInfoReqVO plotInfoReqVO);

    ChargingPileVO queryChargingPileData(String key);

    ChargingPile queryChargingPile(String chargingPileNo);

    String queryConfigByKey(String configKey);

    NewsDto queryNewsByNewsConfigKey(String newsConfigKey, String type);

    List<NewsDto> queryNewsNameList(String type);

}