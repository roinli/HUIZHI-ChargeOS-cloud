package com.jingli.inter.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.inter.domain.UserOrderVO;
import com.jingli.inter.entity.ChargingOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

 /**
 * 充电订单表;(c_charging_order)表数据库访问层
 * @author : Abdulla
 * @date : 2023-8-25
 */
@Mapper
public interface ChargingOrderMapper{
    int insert(ChargingOrder chargingOrder);
    int update(ChargingOrder chargingOrder);


     Page<UserOrderVO> queryUserOrderListPage(@Param("searchOpenId") String searchOpenId,@Param("searchOrderStatus") String searchOrderStatus, @Param("mobile") String mobile, Page<UserOrderVO> objectPage);
 }