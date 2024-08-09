package com.jingli.inter.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONObject;
import com.jingli.inter.domain.*;
import com.jingli.inter.entity.ChargingPile;
import com.jingli.inter.enums.SortTypeEnum;
import com.jingli.inter.mapper.ChargingPileMapper;
import com.jingli.inter.service.ChargingPileService;
import com.jingli.inter.service.ChargingPortService;
import com.jingli.inter.service.CustomPriceService;
import com.jingli.inter.vo.req.PlotInfoReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 充电桩表;(c_charging_pile)表服务实现类
 *
 */
@Service
@Slf4j
public class ChargingPileServiceImpl implements ChargingPileService {
    @Autowired
    private ChargingPileMapper chargingPileMapper;
    @Autowired
    private ChargingPortService chargingPortService;
    @Autowired
    private CustomPriceService customPriceService;

    @Override
    public List<PlotVO> getPlotInfo(PlotInfoReqVO plotInfoReqVO) {
        List<PlotVO> plotList = chargingPileMapper.queryPlotInfoByVo(plotInfoReqVO);
        if (CollUtil.isEmpty(plotList)) {
            log.info("查询无充电桩数据!");
            return Collections.EMPTY_LIST;
        }
        // 返回给前端的小区
        List<PlotVO> returnPlot = new ArrayList<>();
        for (PlotVO plotVo : plotList) {
            Integer plotId = plotVo.getId();
            plotInfoReqVO.setPlotId(plotId);
            List<PilePortVO> ports = chargingPortService.queryPortInfoVo(plotInfoReqVO);
            if (CollUtil.isEmpty(ports)) {
                continue;
            }
            // 计算距离
            List<PilePortVO> noBusy = ports.stream().filter(s -> "N".equals(s.getState())).collect(Collectors.toList());
            List<PilePortVO> slow = ports.stream().filter(s -> s.getPileType().equals(0)).collect(Collectors.toList());
            List<PilePortVO> fast = ports.stream().filter(s -> s.getPileType().equals(1)).collect(Collectors.toList());
            plotVo.setTotalNum(ports.size());
            plotVo.setNotBusyNum(noBusy.size());
            plotVo.setSlowNum(slow.size());
            plotVo.setFastNum(fast.size());
            if (plotInfoReqVO.getDeviceType() == 4) {
                PriceVO priceDto = getPriceInfo(plotId.toString(), String.valueOf(plotInfoReqVO.getDeviceType()));
                if (ObjectUtil.isNotNull(priceDto)) {
                    plotVo.setPrice(priceDto.getPrice());
                    plotVo.setServicePrice(priceDto.getServicePrice());
                }
            } else {
                if (CollUtil.isNotEmpty(ports)  ){
                    PilePortVO pilePortVO = ports.get(0);
                    if (ObjUtil.isNotEmpty(pilePortVO)){
                        float hours = pilePortVO.getHours();
                        plotVo.setPrice(hours);
                    }

                }

            }
            plotVo.setDeviceType(plotInfoReqVO.getDeviceType());
            returnPlot.add(plotVo);
        }
        if (CollUtil.isNotEmpty(returnPlot)) {
            if (SortTypeEnum.ONE.getCode().equals(plotInfoReqVO.getSortType())) {
                returnPlot.sort(Comparator.comparing(PlotVO::getDistance));
            } else if (SortTypeEnum.ONE.getCode().equals(plotInfoReqVO.getSortType())) {
                returnPlot.sort(Comparator.comparing(PlotVO::getPrice));
            } else {
                returnPlot.sort(Comparator.comparing(PlotVO::getDistance));
                returnPlot.sort(Comparator.comparing(PlotVO::getPrice));
            }

        }
        return returnPlot;
    }


    @Override
    public ChargingPileVO queryChargingPileData(String key) {
        return chargingPileMapper.queryChargingPileData(key);
    }

    @Override
    public ChargingPile queryChargingPile(String chargingPileNo) {
        return chargingPileMapper.queryChargingPile(chargingPileNo);
    }


    private PriceVO getPriceInfo(String plotId, String deviceType) {
        PriceVO price = null;
        HashMap<String, String> map = new HashMap<>(4);
        map.put("plotId", plotId);
        map.put("deviceType", deviceType);
        float hour = DateUtil.hour(new Date(), true) + DateUtil.minute(new Date()) / 60f;
        log.info("当前获取到的时间:" + hour);
        LocalDateTime now = LocalDateTime.now();
        log.info("当前获取到的时间Local:" + hour);
        // 获取当前小时
        int hour1 = now.getHour();
        List<PriceVO> priceList = customPriceService.queryPriceByPlotId(map);
        log.info("价格列表:" + JSONObject.toJSONString(priceList));
        for (PriceVO customPrice : priceList) {
            String rangeTime = customPrice.getRangeTime();
            if (StrUtil.isNotBlank(rangeTime)) {
                List<RangeTimeVO> timeDtoList = JSONUtil.toList(rangeTime, RangeTimeVO.class);
                for (RangeTimeVO rangeTimeDto : timeDtoList) {
                    if (rangeTimeDto.getStartTime() <= hour1 && rangeTimeDto.getEndTime() > hour1) {
                        price = customPrice;
                        price.setStartTime(rangeTimeDto.getStartTime());
                        price.setEndTime(rangeTimeDto.getEndTime());
                        break;
                    }
                }
            }
        }
        return price;
    }

    @Override
    public String queryConfigByKey(String configKey) {
        return chargingPileMapper.queryConfigByKey(configKey);
    }

    @Override
    public NewsDto queryNewsByNewsConfigKey(String newsConfigKey, String type) {
        return chargingPileMapper.queryNewsByNewsConfigKey(newsConfigKey, type);
    }

    @Override
    public List<NewsDto> queryNewsNameList(String type) {
        return chargingPileMapper.queryNewsNameList(type);
    }

}