package com.hcp.simulator.common;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.hcp.common.core.exception.base.BaseException;
import com.hcp.simulator.dto.ChargingOrderDTO;
import com.hcp.simulator.service.ChargingOrderService;
import com.hcp.system.api.domain.ChargingOrder;
import com.hcp.system.api.domain.ChargingPile;
import com.hcp.system.api.domain.ChargingPort;
import com.hcp.simulator.service.ChargingPileService;
import com.hcp.simulator.service.ChargingPortService;
import com.hcp.simulator.simulation.SimPileClientImpl;
import com.hcp.system.api.domain.vo.ChargingPileVO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
@Configuration
public class SimCenter {

    private static final Map<String, SimPileClientImpl> pileMap = new ConcurrentHashMap<>();

    @Autowired
    private ChargingPileService chargingPileService;
    @Autowired
    private ChargingPortService chargingPortService;
    @Autowired
    private ChargingOrderService chargingOrderService;

    public Boolean isOnline(String pileId) {
        if (pileMap.containsKey(pileId)) {
            return true;
        } else {
            return false;
        }
    }

    public void start(String pileId) throws BaseException {
        log.info("启动模态充电桩：{}", pileId);
        if (pileMap.containsKey(pileId)) {
            throw new BaseException("模态桩已启动");
        }
        ChargingPile chargingPile = chargingPileService.getById(pileId);
        if(chargingPile == null){
            throw new BaseException("未查询到充电桩信息");
        }
        List<ChargingPort> chargingPorts = chargingPortService.getByDeviceId(pileId);
        if(CollectionUtil.isEmpty(chargingPorts)){
            throw new BaseException("未查询到端口信息");
        }
        SimPileClientImpl pileClient = new SimPileClientImpl(chargingPile, chargingPorts);
        pileMap.put(pileId, pileClient);
        try {
            pileClient.start();
            log.info("模态桩服务------启动成功");
            chargingPileService.updateRunningStatus(pileId, 0L);
            for (ChargingPort chargingPort : chargingPorts) {
                chargingPort.setState("N");
                chargingPort.setGunStatus(0L);
                chargingPort.setCanUse(0L);
            }
            chargingPortService.updateBatchById(chargingPorts);
            chargingOrderService.updateNoEndOrder(pileId);
        } catch (BaseException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BaseException("未知错误");
        }
    }

    public void stop(String pileId) throws BaseException {
        if (!pileMap.containsKey(pileId)) {
            throw new BaseException("模态桩未启动");
        }
        try {
            SimPileClientImpl pileClient = pileMap.get(pileId);
            pileClient.stop();
            chargingPileService.updateRunningStatus(pileId, 1L);
        } finally {
            pileMap.remove(pileId);
        }
    }

    public void link(String pileId,String deviceId) throws BaseException {
        if (!pileMap.containsKey(pileId)) {
            throw new BaseException("模态桩未启动");
        }
        SimPileClientImpl pileClient = pileMap.get(pileId);
        if (!pileClient.portIsExist(deviceId)) {
            throw new BaseException("充电枪不存在");
        }
        if(pileClient.portIsCharging(deviceId)){
            throw new BaseException("充电枪正在充电中");
        }
        Long gunStatus = pileClient.link(deviceId);
        chargingPortService.updateGunStatus(pileId, deviceId, gunStatus,"Y");
    }

    public void unlink(String pileId,String deviceId) throws BaseException {
        if (!pileMap.containsKey(pileId)) {
            throw new BaseException("模态桩未启动");
        }
        SimPileClientImpl pileClient = pileMap.get(pileId);
        if (!pileClient.portIsExist(deviceId)) {
            throw new BaseException("充电枪不存在");
        }
        if(pileClient.portIsCharging(deviceId)){
            throw new BaseException("充电中无法拔枪");
        }
        Long gunStatus = pileClient.unlink(deviceId);
        chargingPortService.updateGunStatus(pileId, deviceId, gunStatus,"N");
    }

    public void startCharge(ChargingOrderDTO chargingOrder) throws BaseException {
        if (chargingOrder == null || StringUtils.isBlank(chargingOrder.getPileId()) || StringUtils.isBlank(chargingOrder.getDeviceId())) {
            throw new BaseException("无效的枪信息");
        }
        if (!pileMap.containsKey(chargingOrder.getPileId())) {
            throw new BaseException("模态桩未启动");
        }
        SimPileClientImpl pileClient = pileMap.get(chargingOrder.getPileId());
        if (!pileClient.portIsExist(chargingOrder.getDeviceId())) {
            throw new BaseException("充电枪不存在");
        }
        if (pileClient.portIsUse(chargingOrder.getDeviceId()) && pileClient.portIsCharging(chargingOrder.getDeviceId())) {
            throw new BaseException("充电口正在使用");
        }
        ChargingOrder dbChargingOrder = new ChargingOrder();
        BeanUtil.copyProperties(chargingOrder, dbChargingOrder);

        ChargingPile pile =  chargingPileService.getById(chargingOrder.getPileId());

        //订单有ID证明已有订单,没有订单ID证明是新增ID
        if(StringUtils.isBlank(chargingOrder.getOrderId())){
            dbChargingOrder.setOrderId(IdUtil.getSnowflakeNextIdStr());
            chargingOrder.setOrderId(dbChargingOrder.getOrderId());
            dbChargingOrder.setOrderState("1");
            dbChargingOrder.setStartTime(DateUtil.formatDateTime(new Date()));
            dbChargingOrder.setOrderNumber("TEST" + System.currentTimeMillis());
            dbChargingOrder.setChargeStatus("9001");
            dbChargingOrder.setIsFee("2002");
            dbChargingOrder.setDeviceType(4L);
            dbChargingOrder.setPortId(pileClient.getPortIdByDeviceId(chargingOrder.getDeviceId()));
            if (pile !=null && pile.getUserId()!=null){
                dbChargingOrder.setUserId(pile.getUserId());
            }
            chargingOrderService.save(dbChargingOrder);
        }
        Long gunStatus = pileClient.startCharge(chargingOrder);
        chargingPortService.updateGunStatus(chargingOrder.getPileId(), chargingOrder.getDeviceId(), gunStatus,"Y");
    }

    public void stopCharge(String pileId,String deviceId) throws BaseException {
        if (!pileMap.containsKey(pileId)) {
            throw new BaseException("模态桩未启动");
        }
        SimPileClientImpl pileClient = pileMap.get(pileId);
        if (!pileClient.portIsExist(deviceId)) {
            throw new BaseException("充电枪不存在");
        }
        Long gunStatus = pileClient.stopCharge(deviceId);
        chargingPortService.updateGunStatus(pileId, deviceId, gunStatus,"N");
    }
}
