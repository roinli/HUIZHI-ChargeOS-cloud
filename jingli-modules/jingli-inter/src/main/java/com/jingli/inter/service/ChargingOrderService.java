package com.jingli.inter.service;

import com.jingli.inter.domain.ApiResult;
import com.jingli.inter.domain.PageVo;
import com.jingli.inter.entity.ChargingPile;

/**
 * 充电订单表;(c_charging_order)表服务接口
 */
public interface ChargingOrderService{


     ApiResult<String> saveOrder(String searchOpenId, String orderOrderType, String orderPortId, String orderHour, String chargingPileNo, ChargingPile chargingPile, String mobile, String platform);


     ApiResult<PageVo> queryOrderList(String searchOpenId, String searchPage, String searchOrderStatus, String mobile);


 }