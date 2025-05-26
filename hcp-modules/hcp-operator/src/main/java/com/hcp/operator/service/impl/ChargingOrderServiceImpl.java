package com.hcp.operator.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONObject;
import com.hcp.common.core.domain.R;
import com.hcp.common.core.utils.uuid.Seq;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.security.utils.SecurityUtils;
import com.hcp.operator.constant.ChargeStatus;
import com.hcp.operator.constant.FeeConstant;
import com.hcp.operator.constant.OrderState;
import com.hcp.operator.domain.ManagerTotalDataVO;
import com.hcp.operator.domain.QueryChargePileVo;
import com.hcp.operator.mapper.*;
import com.hcp.operator.service.IChargingPileService;
import com.hcp.operator.service.IChargingPortService;
import com.hcp.operator.utils.RandomUtil;
import com.hcp.operator.websocket.WebSocket;
import com.hcp.operator.websocket.bean.MessageDto;
import com.hcp.system.api.RemoteSimulatorService;
import com.hcp.system.api.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.service.IChargingOrderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
import org.springframework.transaction.annotation.Transactional;

/**
 * 充电订单Service业务层处理
 *
 * @author hcp
 * @date 2024-08-06
 */
@Slf4j
@Service
public class ChargingOrderServiceImpl implements IChargingOrderService
{
    @Autowired
    private ChargingOrderMapper chargingOrderMapper;
    @Autowired
    private OrderLogMapper orderLogMapper;
    @Autowired
    private ChargingPortMapper chargingPortMapper;
    @Autowired
    private ChargingPileMapper chargingPileMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private RemoteSimulatorService simulatorService;

    /**
     * 查询充电订单
     *
     * @param orderId 充电订单主键
     * @return 充电订单
     */
    @Override
    public ChargingOrder selectChargingOrderByOrderId(String orderId)
    {
        return chargingOrderMapper.selectById(orderId);
    }
    @Override
    public AjaxResult selectChargingOrderByOrderNumber(String orderNumber)
    {
        ChargingOrder order = chargingOrderMapper.selectChargingOrderByOrderNumber(orderNumber);
        if(Objects.isNull(order)){
            AjaxResult.error("订单不存在");
        }
        // 查询订单日志数据
        List<OrderLog> logList = orderLogMapper.selectOrderLogListByOrderNumber(orderNumber);
        if(CollUtil.isNotEmpty(logList)){
            logList.sort(Comparator.comparing(OrderLog::getCreateTime));
            order.setLogList(logList);
        }
        return AjaxResult.success(order);
    }

    /**
     * 查询充电订单列表-分页
     *
     * @param chargingOrder 充电订单
     * @return 充电订单
     */
    @Override
    public IPage<ChargingOrder> selectChargingOrderPage(ChargingOrder chargingOrder)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return chargingOrderMapper.selectChargingOrderListPage(mpPage,chargingOrder);
    }
    /**
     * 查询充电订单列表
     *
     * @param chargingOrder 充电订单
     * @return 充电订单
     */
    @Override
    public List<ChargingOrder> selectChargingOrderList(ChargingOrder chargingOrder)
    {
        return chargingOrderMapper.selectChargingOrderList(chargingOrder);
    }

    /**
     * 新增充电订单
     *
     * @param chargingOrder 充电订单
     * @return 结果
     */

    @Override
    public int insertChargingOrder(ChargingOrder chargingOrder)
    {
        return chargingOrderMapper.insert(chargingOrder);
    }

    /**
     * 修改充电订单
     *
     * @param chargingOrder 充电订单
     * @return 结果
     */
    @Override
    public int updateChargingOrder(ChargingOrder chargingOrder)
    {
        return chargingOrderMapper.updateById(chargingOrder);
    }

    /**
     * 批量删除充电订单
     *
     * @param orderIds 需要删除的充电订单主键
     * @return 结果
     */
    @Override
    public int deleteChargingOrderByOrderIds(String[] orderIds)
    {
        return chargingOrderMapper.deleteChargingOrderByOrderIds(orderIds);
    }

    /**
     * 删除充电订单信息
     *
     * @param orderId 充电订单主键
     * @return 结果
     */
    @Override
    public int deleteChargingOrderByOrderId(String orderId)
    {
        return chargingOrderMapper.deleteById(orderId);
    }

    @Override
    public ManagerTotalDataVO getManageTotalData(QueryChargePileVo pileVo) {

        Long userId = SecurityUtils.getUserId();
        // 非管理员或未选择用户 则查询当前用户数据
        if (!SysUser.isAdmin(userId) && pileVo.getUserId()==null)
        {
            pileVo.setUserId(userId);
        }
        ManagerTotalDataVO vo = chargingOrderMapper.getManageTotalData(pileVo);

        vo.setSumSales(vo.getSumSales().setScale(2, RoundingMode.HALF_UP));
        vo.setReSales(vo.getReSales().setScale(2, RoundingMode.HALF_UP));

        vo.setRealSales(vo.getSumSales().subtract(vo.getReSales()).setScale(2,RoundingMode.HALF_UP));

        vo.setChargeFee(vo.getChargeFee().setScale(2, RoundingMode.HALF_UP));
        vo.setServiceFee(vo.getServiceFee().setScale(2, RoundingMode.HALF_UP));

        vo.setConsumePower(vo.getConsumePower().setScale(2,RoundingMode.HALF_UP));
        vo.setChargeTimes(vo.getSumCount());


        Integer realHour = Integer.valueOf(vo.getRealHour());
        String lastTotalHour  =    realHour / 60 + "小时" + realHour % 60 + "分钟";

        vo.setRealHour(lastTotalHour);

        return vo;
    }

    @Override
    public ChargingOrder startChargeOrder(String pileId, String port,Long userId, BigDecimal amount,Integer hour) {
        ChargingPile chargingPile = chargingPileMapper.getById(pileId);
        Assert.notNull(chargingPile,"充电桩未录入");
        ChargingPort chargingPort = chargingPortMapper.selectPort(pileId, port);
        Assert.notNull(chargingPort,"接口信息不存在");
        if (null==userId){
            userId = SecurityUtils.getUserId();
        }
        ChargingOrder chargingOrder = new ChargingOrder();
//        String orderId = pileId.concat(port).concat(LocalDateTimeUtil.format(LocalDateTime.now(),"yyyyMMddHHmmss")).concat(Seq.getId(new AtomicInteger(1),2));
        String orderNumber = RandomUtil.getRandomNumber(32);
        chargingOrder.setOrderId(orderNumber);

        chargingOrder.setUserId(userId);
        chargingOrder.setOrderNumber(orderNumber);
        chargingOrder.setOrderState(OrderState.PLACE);
        chargingOrder.setPileId(pileId);
        chargingOrder.setDeviceType(4L);
        chargingOrder.setPortId(chargingPort.getPortId());
        chargingOrder.setStartTime(LocalDateTimeUtil.format(LocalDateTime.now(),"yyyy-MM-dd HH:mm:ss"));
        chargingOrder.setChargeStatus(ChargeStatus.PRE_CHARGE);
        chargingOrder.setIsFee(FeeConstant.FREE_FEE);
        chargingOrder.setHour(String.valueOf(hour));
        chargingOrder.setTenantId(chargingPort.getTenantId());
        chargingOrderMapper.insertOrder(chargingOrder);
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderNumber(orderNumber);
        orderLog.setMainProcess(1L);
        orderLog.setBriefInfo("客户下单");
        orderLog.setLogContent("用户下单");
        orderLog.setTenantId(chargingPort.getTenantId());
        orderLog.setCreateTime(new Date());
        orderLogMapper.insertOrderLog(orderLog);
        //启动模拟器充电
        chargingOrder.setDeviceId(port);
        R<String> stringR = simulatorService.startCharge(chargingOrder);
        log.info("模拟器启动结果:{}", JSONObject.toJSONString(stringR));
        return chargingOrder;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopChargingOrder(String pileId, String port) {
        ChargingPort chargingPort = chargingPortMapper.selectPort(pileId, port);
        Assert.notNull(chargingPort,"充电口信息为空");
        ChargingOrder  order = chargingOrderMapper.findChargingOrder(pileId,chargingPort.getPortId(),ChargeStatus.CHARGING);
        Assert.notNull(order,"充电中订单信息未找到");
        chargingOrderMapper.updateByPrimaryKey(order);
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderNumber(order.getOrderNumber());
        orderLog.setMainProcess(1L);
        orderLog.setBriefInfo("客户结束充电");
        orderLog.setLogContent("用户手动结束充电,结算中");
        orderLog.setTenantId(chargingPort.getTenantId());
        orderLog.setCreateTime(new Date());
        orderLogMapper.insertOrderLog(orderLog);
        R<String> stringR = simulatorService.stopCharge(pileId, port);
        log.info("模拟器停止充电结果:{}", JSONObject.toJSONString(stringR));
    }

    @Override
    public void handleOrderInfo(String orderId, Double totalPower, BigDecimal totalAmount, BigDecimal electricFee, BigDecimal serviceFee, String startTime, String endTime, String stopReason) {
        ChargingOrder order = chargingOrderMapper.getById(orderId);
        Assert.notNull(order,"订单信息为空");
        order.setConsumePower(new BigDecimal(totalPower));
        order.setOrdergold(String.valueOf(totalAmount));
        order.setPrice(String.valueOf(electricFee));
        order.setServiceFee(serviceFee);
        order.setRealEndTime(DateUtil.parseDateTime(endTime));
        long realHour = DateUtil.between(DateUtil.parseDateTime(startTime), DateUtil.parseDateTime(endTime), DateUnit.HOUR);
        order.setRealHour(String.valueOf(realHour));
        order.setOrderState(OrderState.PAYED);
        order.setChargeStatus(ChargeStatus.FINISH_CHARGE);
        order.setPayTime(new Date());
        order.setRealEndTime(DateUtil.parseDateTime(endTime));
        chargingOrderMapper.updateByPrimaryKey(order);
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderNumber(order.getOrderNumber());
        orderLog.setMainProcess(1L);
        orderLog.setBriefInfo("完成订单");
        orderLog.setLogContent("已完成订单,消费金额:"+totalAmount);
        orderLog.setTenantId(order.getTenantId());
        orderLog.setCreateTime(new Date());
        orderLogMapper.insertOrderLog(orderLog);
    }

    @Override
    public void updateOrderStart(String orderId, String pileId, String port, String startResult, String failReason) {
        ChargingOrder order = chargingOrderMapper.getById(orderId);
        Assert.notNull(order,"订单信息为空");
        order.setChargeStatus(ChargeStatus.CHARGING);
        chargingOrderMapper.updateByPrimaryKey(order);
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderNumber(order.getOrderNumber());
        orderLog.setMainProcess(1L);
        orderLog.setCreateTime(new Date());
        orderLog.setBriefInfo("开始充电");
        orderLog.setLogContent("远程充电启动，启动结果:"+startResult+",失败原因:"+failReason);
        orderLog.setTenantId(order.getTenantId());
        orderLogMapper.insertOrderLog(orderLog);
    }

    @Override
    public void updateOrderStop(String pileId, String port, String stopResult, String failReason) {
        ChargingPort chargingPort = chargingPortMapper.selectPort(pileId, port);
        Assert.notNull(chargingPort,"充电口信息为空");
        ChargingOrder  order = chargingOrderMapper.findChargingOrder(pileId,chargingPort.getPortId(),ChargeStatus.CHARGING);
        Assert.notNull(order,"充电中订单信息未找到");
        order.setEndTime(LocalDateTimeUtil.format(LocalDateTime.now(),"yyyy-MM-dd HH:mm:ss"));
        order.setOrderState(OrderState.SETTLE);
        chargingOrderMapper.updateByPrimaryKey(order);
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderNumber(order.getOrderNumber());
        orderLog.setMainProcess(1L);
        orderLog.setCreateTime(new Date());
        orderLog.setBriefInfo("充电结束");
        orderLog.setLogContent("远程充电结束，关闭结果:"+stopResult+",失败原因:"+failReason);
        orderLog.setTenantId(order.getTenantId());
        orderLogMapper.insertOrderLog(orderLog);
    }

    @Override
    public void handleChargingHeartBeat(String orderId, Double voltage, Double electric, String soc, Double chargePower, BigDecimal chargeFee, BigDecimal serviceFee,BigDecimal chargePrice) {
        log.info("收到实时充电数据:订单号:{},电压:{},电流:{},soc:{},已充电量:{},已充金额:{}",orderId,voltage,electric,soc,chargePower,chargePrice);
        ChargingOrder order = chargingOrderMapper.getById(orderId);
        Assert.notNull(order,"订单信息为空");
        order.setChargeStatus(ChargeStatus.CHARGING);
        order.setChargeFee(chargeFee);
        order.setServiceFee(serviceFee);
        order.setOrdergold(String.valueOf(chargePrice));
        order.setChargingCurrent(String.valueOf(electric));
        order.setConsumePower(BigDecimal.valueOf(chargePower));
        order.setHour(String.valueOf(DateUtil.between(DateUtil.parseDateTime(order.getStartTime()),new Date(), DateUnit.HOUR)));
        long diffInMillis = DateUtil.between(DateUtil.parseDateTime(order.getStartTime()), new Date(), DateUnit.MS);
        double diffInHours = diffInMillis / 3600000.0;
        DecimalFormat df = new DecimalFormat("0.00");
        String formattedHours = df.format(diffInHours);
        order.setRealHour(formattedHours);
        order.setChargingCdgl(String.valueOf(NumberUtil.mul(voltage,electric)));
        chargingOrderMapper.updateByPrimaryKey(order);
        MessageDto messageDto  = MessageDto.builder().hasChargePower(BigDecimal.valueOf(chargePower))
                .totalFee(chargePrice).chargeMin((int) DateUtil.between(DateUtil.parseDateTime(order.getStartTime()),new Date(),DateUnit.MINUTE)).preEndMin(12).serviceFee(serviceFee).powerFee(chargeFee)
                .soc(Integer.valueOf(soc)).realTimePower(NumberUtil.mul(voltage,electric)).voltage(voltage.floatValue()).electricity(electric.floatValue()).build();
        WebSocket.sendMessageToOrder(orderId, JSONUtil.toJsonStr(messageDto));
    }

    @Override
    public Page<ChargingOrder> queryOrderList(ChargingOrder chargingOrder) {
        Page mpPage =new Page(chargingOrder.getPageNo(),chargingOrder.getPageSize());
        return chargingOrderMapper.selectChargingOrderListPage(mpPage,chargingOrder);
    }
}
