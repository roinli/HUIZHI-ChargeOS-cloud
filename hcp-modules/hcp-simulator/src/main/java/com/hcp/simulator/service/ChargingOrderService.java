package com.hcp.simulator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hcp.system.api.domain.ChargingOrder;

/**
* @author: fengzhangbin
* @description: 针对表【c_charging_order(充电订单表)】的数据库操作Service
* @createDate: 2024-08-09
*/
public interface ChargingOrderService extends IService<ChargingOrder> {

    void updateNoEndOrder(String pileId);

}
