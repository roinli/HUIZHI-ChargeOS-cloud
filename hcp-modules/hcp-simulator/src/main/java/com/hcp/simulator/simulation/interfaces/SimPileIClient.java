package com.hcp.simulator.simulation.interfaces;


import com.hcp.common.core.exception.base.BaseException;
import com.hcp.simulator.dto.ChargingOrderDTO;

public interface SimPileIClient {

    /**
     * 充电桩启动
     */
    void start() throws BaseException;

    /**
     * 充电桩停止（断电）
     */
    void stop() throws BaseException;

    /**
     * 充电桩-车插枪
     */
    Long link(String deviceId) throws BaseException;

    /**
     * 充电桩-车拔枪
     */
    Long unlink(String deviceId) throws BaseException;

    /**
     * 启动充电
     */
    Long startCharge(ChargingOrderDTO chargingOrder) throws BaseException;

    /**
     * 停止充电
     */
    Long stopCharge(String deviceId) throws BaseException;

    void sendRealTimeData(Boolean sendForce,String deviceId) throws BaseException;

    void sendTradeInfo(String deviceId,String stopReason) throws BaseException;

    void startHeart() throws BaseException;

    /**
     * 判断充电枪是否存在
     */
    boolean portIsExist(String deviceId);

    /**
     * 判断充电枪是否正在使用
     */
    boolean portIsUse(String deviceId);

    /**
     * 判断充电枪是否正在充电
     */
    boolean portIsCharging(String deviceId);

    /**
     * 根据设备ID获取端口Id
     */
    Long getPortIdByDeviceId(String deviceId);

}
