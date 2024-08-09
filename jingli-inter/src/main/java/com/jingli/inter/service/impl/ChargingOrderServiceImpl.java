package com.jingli.inter.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.inter.constant.Constance;
import com.jingli.inter.domain.ApiResult;
import com.jingli.inter.domain.PageVo;
import com.jingli.inter.domain.ResultStatus;
import com.jingli.inter.domain.UserOrderVO;
import com.jingli.inter.entity.ChargingOrder;
import com.jingli.inter.entity.ChargingPile;
import com.jingli.inter.entity.ChargingPort;
import com.jingli.inter.entity.CustomPrice;
import com.jingli.inter.mapper.ChargingOrderMapper;
import com.jingli.inter.mapper.ChargingPortMapper;
import com.jingli.inter.mapper.CustomPriceMapper;
import com.jingli.inter.service.ChargingOrderService;
import com.jingli.inter.utils.DateUtil;
import com.jingli.inter.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 充电订单表;(c_charging_order)表服务实现类
 */
@Service
@Slf4j
public class ChargingOrderServiceImpl implements ChargingOrderService {
    @Autowired
    private ChargingOrderMapper chargingOrderMapper;
    @Autowired
    private ChargingPortMapper chargingPortMapper;
    @Autowired
    private CustomPriceMapper customPriceMapper;

    /**
     * 新增数据
     *
     * @param chargingOrder 实例对象
     * @return 实例对象
     */
    public ChargingOrder insert(ChargingOrder chargingOrder){
        chargingOrderMapper.insert(chargingOrder);
        return chargingOrder;
    }

     @Override
     public ApiResult<String> saveOrder(String searchOpenId, String orderOrderType, String orderPortId, String orderHour, String chargingPileNo, ChargingPile chargingPile, String mobile, String platform) {
         // 1.5 计算当前订单的金额
         CustomPrice orderPrice = new CustomPrice();
         orderPrice.setChargePileId(chargingPileNo);
         // 查询公众号订单价格
         List<CustomPrice> priceList = customPriceMapper.queryWxOrderPrice(orderPrice);

         // 获取当前的价格
         orderPrice = priceList.get(0);
         // 订单金额
         BigDecimal orderGold ;
         // 设备类型
         String deviceType = String.valueOf(chargingPile.getDeviceType());
         // 每小时单价
         BigDecimal price = BigDecimal.TEN;
         // 如果是四轮车类型，价格是服务费+电费
         if(deviceType.equals(Constance.DEVICE_TYPE_FOUR)){
             price = new BigDecimal(orderPrice.getPrice()).add(orderPrice.getServicePrice());
         } else {
             if(StrUtil.isNotBlank(orderPrice.getHours())){
                 price = new BigDecimal(orderPrice.getHours());
             }
         }
         // 充电时间（用户选择）
         String hour = orderHour;
         ChargingOrder chargingOrder = new ChargingOrder();
         chargingOrder.setOrderType(orderOrderType);
         chargingOrder.setMobile(mobile);
         // 价格计算，如果是按小时计费的
         if(Constance.ORDER_FEE_TYPE_HOURS.equals(orderOrderType)){
             orderGold = price.multiply(new BigDecimal(hour));
             chargingOrder.setHour(hour);
         }else{
             orderGold = price;
             // 给订单插入12小时
             chargingOrder.setHour(Constance.CHARGE_KW_HOURS);
             hour = Constance.CHARGE_KW_HOURS;
         }
         chargingOrder.setOrdergold(orderGold.toString());
         chargingOrder.setPrice(orderPrice.getHours());
         chargingOrder.setCustomPriceId(String.valueOf(orderPrice.getId()));
         // 设置订单类型
         chargingOrder.setDeviceType(chargingPile.getDeviceType());
         // 2.1.1 判断充电桩是否收费，决定订单是收费订单还是免费订单
         String cpFeeType = chargingPile.getCpFeeType();
         chargingOrder.setIsFee(cpFeeType);
         // 2.2.0 计算充电结束时间，开始时间直接使用系统时间
         String startTime = DateUtil.getFfffMmDdhms(new Date());
         chargingOrder.setStarttime(startTime);
         // 计算出结束时间
         String endTime  = DateUtil.getTimeSum(startTime,hour);
         chargingOrder.setEndtime(endTime);
         ChargingPort chargingPort = chargingPortMapper.queryById(Integer.valueOf(orderPortId));
         // 2.3 插入订单
         // 2.3.1 订单号生成
         String orderNumber = RandomUtil.getRandomNumber(8);
         if(deviceType.equals(Constance.DEVICE_TYPE_FOUR)){
             orderNumber = RandomUtil.getRandomNumber(32);
         }
         chargingOrder.setParkid(chargingPileNo);
         chargingOrder.setParklockid(String.valueOf(chargingPort.getId()));
         chargingOrder.setWeixinOpenid(searchOpenId);
         chargingOrder.setCreatetime(new Date());
         chargingOrder.setOrdernumber(orderNumber);
         chargingOrder.setOrderstate(Constance.ORDER_STATUS_HAS_PAYED);
         chargingOrder.setOrderSource(1);
         // 2.3.2 插入订单编写
         chargingOrder.setChargeStatus("9003");
         chargingOrderMapper.insert(chargingOrder);
         return ApiResult.success(orderNumber);
     }

    @Override
    public ApiResult<PageVo> queryOrderList(String searchOpenId, String searchPage, String searchOrderStatus, String mobile) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Page<UserOrderVO> objectPage = new Page<>(Integer.parseInt(searchPage) + 1,5);
            Page<UserOrderVO>  pageList = chargingOrderMapper.queryUserOrderListPage(searchOpenId,searchOrderStatus,mobile,objectPage);
            PageVo queryForPage = new PageVo(Integer.parseInt(searchPage),5);
            queryForPage.setData(pageList.getRecords());
            queryForPage.setTotalRows(Integer.parseInt(String.valueOf(pageList.getTotal())));
            if(null == queryForPage || null == queryForPage.getData()||queryForPage.getData().size() <=0 ){
                return ApiResult.failure(ResultStatus.USER_HAS_NO_ORDERS_CODE);
            }else{
                List<UserOrderVO> list = queryForPage.getData();
                for(UserOrderVO order:list){
                    // 实际结束时间
                    String actualEndTime = order.getActualendtime();
                    if(StringUtils.isNotBlank(actualEndTime)){
                        order.setEndTime(actualEndTime);
                    }
                    String endTime = order.getEndTime();
                    if(StrUtil.isNotBlank(endTime)){
                        order.setRealHour(DateUtil.getDatePoor(sdf.parse(order.getEndTime()),sdf.parse(order.getStartTime())));
                    }else{
                        order.setRealHour("");
                    }
                }
                return ApiResult.success(queryForPage);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ApiResult.failure();
        }
    }

}