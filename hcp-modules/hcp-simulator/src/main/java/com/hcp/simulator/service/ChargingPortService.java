package com.hcp.simulator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hcp.system.api.domain.ChargingPort;

import java.util.List;

/**
* @author feng
* @description 针对表【c_charging_port(充电桩端口表)】的数据库操作Service
* @createDate 2024-08-10 16:36:46
*/
public interface ChargingPortService extends IService<ChargingPort> {

    List<ChargingPort> getByDeviceId(String pileId);

    void updateGunStatus(String pileId, String deviceId, Long gunStatus, String state);
}
