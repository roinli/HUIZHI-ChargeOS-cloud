package com.hcp.operator.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hcp.common.security.utils.SecurityUtils;
import com.hcp.operator.domain.PileTotalResult;
import com.hcp.operator.domain.QueryChargePileVo;
import com.hcp.operator.enums.SortTypeEnum;
import com.hcp.operator.service.IChargingPortService;
import com.hcp.operator.service.IChargingStationService;
import com.hcp.operator.service.ICustomPriceService;
import com.hcp.operator.utils.DistanceUtil;
import com.hcp.system.api.domain.ChargingStation;
import com.hcp.system.api.domain.SysUser;
import com.hcp.system.api.domain.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.mapper.ChargingPileMapper;
import com.hcp.system.api.domain.ChargingPile;
import com.hcp.operator.service.IChargingPileService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;

import javax.annotation.Resource;

/**
 * 充电桩Service业务层处理
 *
 * @author hcp
 * @date 2024-08-06
 */
@Service
@Slf4j
public class ChargingPileServiceImpl implements IChargingPileService
{
    @Autowired
    private ChargingPileMapper chargingPileMapper;
    @Resource
    private IChargingPortService chargingPortService;
    @Resource
    IChargingStationService chargingStationService;
    @Resource
    ICustomPriceService customPriceService;

    /**
     * 查询充电桩
     *
     * @param pileId 充电桩主键
     * @return 充电桩
     */
    @Override
    public ChargingPile selectChargingPileByPileId(String pileId)
    {
        return chargingPileMapper.selectById(pileId);
    }

    /**
     * 查询充电桩列表-分页
     *
     * @param chargingPile 充电桩
     * @return 充电桩
     */
    @Override
    public IPage<ChargingPile> selectChargingPilePage(ChargingPile chargingPile)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return chargingPileMapper.selectChargingPileList(mpPage,chargingPile);
    }
    /**
     * 查询充电桩列表
     *
     * @param chargingPile 充电桩
     * @return 充电桩
     */
    @Override
    public List<ChargingPile> selectChargingPileList(ChargingPile chargingPile)
    {
        return chargingPileMapper.selectChargingPileList(chargingPile);
    }

    /**
     * 新增充电桩
     *
     * @param chargingPile 充电桩
     * @return 结果
     */

    @Override
    public int insertChargingPile(ChargingPile chargingPile)
    {
        return chargingPileMapper.insert(chargingPile);
    }

    /**
     * 修改充电桩
     *
     * @param chargingPile 充电桩
     * @return 结果
     */
    @Override
    public int updateChargingPile(ChargingPile chargingPile)
    {
        return chargingPileMapper.updateById(chargingPile);
    }

    /**
     * 批量删除充电桩
     *
     * @param pileIds 需要删除的充电桩主键
     * @return 结果
     */
    @Override
    public int deleteChargingPileByPileIds(String[] pileIds)
    {
        return chargingPileMapper.deleteChargingPileByPileIds(pileIds);
    }

    /**
     * 删除充电桩信息
     *
     * @param pileId 充电桩主键
     * @return 结果
     */
    @Override
    public int deleteChargingPileByPileId(String pileId)
    {
        return chargingPileMapper.deleteById(pileId);
    }

    @Override
    public IPage<PileTotalResult> getChargeTotalPage(QueryChargePileVo vo) {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));

        mpPage.setCurrent(vo.getPageNum());
        Long userId = SecurityUtils.getUserId();
        // 非管理员或未选择用户 则查询当前用户数据
        if (!SysUser.isAdmin(userId) && vo.getUserId()==null)
        {
            vo.setUserId(userId);
        }
        IPage<PileTotalResult> pilePageList = chargingPileMapper.getChargeTotal(mpPage, vo);
        return pilePageList;
    }

    public List<PileTotalResult> getChargeTotal(QueryChargePileVo vo) {

        Long userId = SecurityUtils.getUserId();
        // 非管理员或未选择用户 则查询当前用户数据
        if (!SysUser.isAdmin(userId) && vo.getUserId()==null)
        {
            vo.setUserId(userId);
        }
        List<PileTotalResult> pilePageList = chargingPileMapper.getChargeTotal( vo);
        return pilePageList;
    }

    @Override
    public ChargingPile getById(String pileId) {
        return chargingPileMapper.getById(pileId);
    }

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
            Long stationId = plotVo.getStationId();
            plotInfoReqVO.setStationId(stationId);
            // 计算距离
            plotVo.setDistance(DistanceUtil.getDistance(Double.parseDouble(plotInfoReqVO.getLng()), Double.parseDouble(plotInfoReqVO.getLat()), plotVo.getLng(), plotVo.getLat()));
            List<PilePortVO> ports = chargingPortService.queryPortInfoVo(plotInfoReqVO);
            if (CollUtil.isEmpty(ports)) {
                returnPlot.add(plotVo);
                continue;
            }
            // 计算距离
            plotVo.setDistance(DistanceUtil.getDistance(Double.parseDouble(plotInfoReqVO.getLng()), Double.parseDouble(plotInfoReqVO.getLat()), plotVo.getLng(), plotVo.getLat()));
//            List<PilePortVO> noBusy = ports.stream().filter(s -> "N".equals(s.getState())).collect(Collectors.toList());
            List<PilePortVO> slow = ports.stream().filter(s -> s.getPileType().equals(0)).collect(Collectors.toList());
            List<PilePortVO> fast = ports.stream().filter(s -> s.getPileType().equals(1)).collect(Collectors.toList());
            List<PilePortVO> slowToNoBusy = slow.stream().filter(s -> "N".equals(s.getState())).collect(Collectors.toList());
            List<PilePortVO> fastToNoBusy = fast.stream().filter(s -> "N".equals(s.getState())).collect(Collectors.toList());
            plotVo.setSlowToNoBusy(slowToNoBusy.size());
            plotVo.setFastToNoBusy(fastToNoBusy.size());
            plotVo.setSlowNum(slow.size());
            plotVo.setFastNum(fast.size());
            if (plotInfoReqVO.getDeviceType() == 4) {
                PriceVO priceDto = this.getPriceInfo(stationId.toString(), String.valueOf(plotInfoReqVO.getDeviceType()));
                if (ObjectUtil.isNotNull(priceDto)) {
                    plotVo.setPrice(priceDto.getPrice());
                    plotVo.setServicePrice(priceDto.getServicePrice());
                }
            } else {
                if (CollUtil.isNotEmpty(ports)  ){
                    PilePortVO pilePortVO = ports.get(0);
                    if (ObjectUtil.isNotEmpty(pilePortVO)){
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


    private PriceVO getPriceInfo(String plotId, String deviceType) {
        PriceVO price = new PriceVO();
        ChargingStation chargingStation = chargingStationService.selectChargingStationByStationId(Long.valueOf(plotId));

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
    public ChargingPileVO queryChargingPileData(String pileId) {
        return chargingPileMapper.queryChargingPileData(pileId);
    }

    @Override
    public PlotDetailVo plotDetail(String plotId, String deviceType) {
        HashMap<String, String> map = new HashMap<>(4);
        map.put("plotId", plotId);
        map.put("deviceType", deviceType);
        PlotDetailVo plotDetail = chargingPileMapper.queryPlotDetailById(plotId);
        List<PlotPileVo> allPile = chargingPileMapper.queryPileListById(map);
        Float price = 0f;
        Float servicePrice = 0f;
        Float hours = 0f;
        if (!allPile.isEmpty()) {
            // 获取allPile中的最小值
            if ("4".equals(deviceType)) {
                PriceVO priceDto = getPriceInfo(plotId, deviceType);
                if (ObjectUtil.isNotNull(priceDto)) {
                    price = priceDto.getPrice();
                    servicePrice = priceDto.getServicePrice();
                    plotDetail.setStartTime(priceDto.getStartTime());
                    plotDetail.setEndTime(priceDto.getEndTime());
                }
            } else {
                hours = allPile.stream().sorted(Comparator.comparing(PlotPileVo::getHours)).collect(Collectors.toList()).get(0).getHours();
            }
            List<PlotPileVo> slowPileList = allPile.stream().filter(pile -> pile.getPileType() == 0).collect(Collectors.toList());
            List<PlotPileVo> fastPileList = allPile.stream().filter(pile -> pile.getPileType() == 1).collect(Collectors.toList());
            if (!slowPileList.isEmpty()) {
                plotDetail.setSlowPileList(slowPileList);
                Integer slowTotal = 0;
                Integer slowNotBusy = 0;
                for (PlotPileVo plotPileVo : slowPileList) {
                    slowNotBusy += plotPileVo.getNotBusyNum();
                    slowTotal += plotPileVo.getTotalNum();
                }
                plotDetail.setSlowNotBusyNum(slowNotBusy);
                plotDetail.setSlowTotalNum(slowTotal);
            }
            if (!fastPileList.isEmpty()) {
                plotDetail.setFastPileList(fastPileList);
                Integer fastTotal = 0;
                Integer fastNotBusy = 0;
                for (PlotPileVo plotPileVo : fastPileList) {
                    fastNotBusy += plotPileVo.getNotBusyNum();
                    fastTotal += plotPileVo.getTotalNum();
                }
                plotDetail.setFastNotBusyNum(fastNotBusy);
                plotDetail.setFastTotalNum(fastTotal);
            }
        }
        plotDetail.setPrice(price);
        plotDetail.setServicePrice(servicePrice);
        plotDetail.setHours(hours);
        return plotDetail;
    }

    @Override
    public Page<PlotVO> getPlotInfoPage(PlotInfoReqVO plotInfoReqVO) {
        Page page = new Page(plotInfoReqVO.getPageNo(),plotInfoReqVO.getPageSize());
        Page<PlotVO> plotPage = chargingPileMapper.queryPlotInfoByPage(page,plotInfoReqVO);

        if (ObjectUtil.isEmpty(plotPage) || CollUtil.isEmpty(plotPage.getRecords())) {
            log.info("查询无充电桩数据!");
            return plotPage;
        }
        List<PlotVO> plotList = plotPage.getRecords();
        // 返回给前端的小区
//        List<PlotVO> returnPlot = new ArrayList<>();
        for (PlotVO plotVo : plotList) {
            Long stationId = plotVo.getStationId();
            plotInfoReqVO.setStationId(stationId);
            // 计算距离
            plotVo.setDistance(DistanceUtil.getDistance(Double.parseDouble(plotInfoReqVO.getLng()), Double.parseDouble(plotInfoReqVO.getLat()), plotVo.getLng(), plotVo.getLat()));
            List<PilePortVO> ports = chargingPortService.queryPortInfoVo(plotInfoReqVO);
            if (CollUtil.isEmpty(ports)) {
                continue;
            }
            plotVo.setDistance(DistanceUtil.getDistance(Double.parseDouble(plotInfoReqVO.getLng()), Double.parseDouble(plotInfoReqVO.getLat()), plotVo.getLng(), plotVo.getLat()));
//            List<PilePortVO> noBusy = ports.stream().filter(s -> "N".equals(s.getState())).collect(Collectors.toList());
            List<PilePortVO> slow = ports.stream().filter(s -> s.getPileType().equals(0)).collect(Collectors.toList());
            List<PilePortVO> fast = ports.stream().filter(s -> s.getPileType().equals(1)).collect(Collectors.toList());
            List<PilePortVO> slowToNoBusy = slow.stream().filter(s -> "N".equals(s.getState())).collect(Collectors.toList());
            List<PilePortVO> fastToNoBusy = fast.stream().filter(s -> "N".equals(s.getState())).collect(Collectors.toList());
            plotVo.setSlowToNoBusy(slowToNoBusy.size());
            plotVo.setFastToNoBusy(fastToNoBusy.size());
            plotVo.setSlowNum(slow.size());
            plotVo.setFastNum(fast.size());
            if (4 == plotInfoReqVO.getDeviceType() ) {
                PriceVO priceDto = getPriceInfo(stationId.toString(), String.valueOf(plotInfoReqVO.getDeviceType()));
                if (ObjectUtil.isNotNull(priceDto)) {
                    plotVo.setPrice(priceDto.getPrice());
                    plotVo.setServicePrice(priceDto.getServicePrice());
                }
            } else {
                if (CollUtil.isNotEmpty(ports)  ){
                    PilePortVO pilePortVO = ports.get(0);
                    if (ObjectUtil.isNotEmpty(pilePortVO)){
                        float hours = pilePortVO.getHours();
                        plotVo.setPrice(hours);
                    }

                }

            }
            plotVo.setDeviceType(plotInfoReqVO.getDeviceType());
//            returnPlot.add(plotVo);
        }
        if (CollUtil.isNotEmpty(plotList)) {
            if (SortTypeEnum.ONE.getCode().equals(plotInfoReqVO.getSortType())) {
                plotList.sort(Comparator.comparing(PlotVO::getDistance));
            } else if (SortTypeEnum.ONE.getCode().equals(plotInfoReqVO.getSortType())) {
                plotList.sort(Comparator.comparing(PlotVO::getPrice));
            } else {
                plotList.sort(Comparator.comparing(PlotVO::getDistance));
                plotList.sort(Comparator.comparing(PlotVO::getPrice));
            }

        }

        return plotPage;
    }
}
