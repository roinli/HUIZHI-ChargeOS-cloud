package com.hcp.simulator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hcp.system.api.domain.ChargingPile;

/**
* @author feng
* @description 针对表【c_charging_pile(充电桩表)】的数据库操作Service
* @createDate 2024-08-10 16:17:56
*/
public interface ChargingPileService extends IService<ChargingPile> {

    void updateRunningStatus(String pileId, long status);

}
