package com.hcp.operator.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.operator.domain.ManagerTotalDataVO;
import com.hcp.operator.domain.QueryChargePileVo;
import com.hcp.system.api.domain.ChargingOrder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 充电订单Service接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface IChargingOrderService
{
    /**
     * 查询充电订单
     *
     * @param orderId 充电订单主键
     * @return 充电订单
     */
    ChargingOrder selectChargingOrderByOrderId(String orderId);
    AjaxResult selectChargingOrderByOrderNumber(String orderNumber);

    /**
     * 查询充电订单列表-分页
     *
     * @param chargingOrder 充电订单
     * @return 充电订单集合
     */
    IPage<ChargingOrder> selectChargingOrderPage(ChargingOrder chargingOrder);
    /**
     * 查询充电订单列表
     *
     * @param chargingOrder 充电订单
     * @return 充电订单集合
     */
    List<ChargingOrder> selectChargingOrderList(ChargingOrder chargingOrder);

    /**
     * 新增充电订单
     *
     * @param chargingOrder 充电订单
     * @return 结果
     */
    int insertChargingOrder(ChargingOrder chargingOrder);

    /**
     * 修改充电订单
     *
     * @param chargingOrder 充电订单
     * @return 结果
     */
    int updateChargingOrder(ChargingOrder chargingOrder);

    /**
     * 批量删除充电订单
     *
     * @param orderIds 需要删除的充电订单主键集合
     * @return 结果
     */
    int deleteChargingOrderByOrderIds(String[] orderIds);

    /**
     * 删除充电订单信息
     *
     * @param orderId 充电订单主键
     * @return 结果
     */
    int deleteChargingOrderByOrderId(String orderId);


    /**
     * @Author adl
     * @Description 经营管理 首页数据统计
     * @Date 2024/8/6 19:00
     * @Param [params]
     */
    ManagerTotalDataVO getManageTotalData(QueryChargePileVo pileVo);

    //生成订单信息
    ChargingOrder startChargeOrder(String pileId, String port,Long userId, BigDecimal amount,Integer hour);

    //充电停止
    void stopChargingOrder(String pileId, String port);

    //结算订单信息
    void handleOrderInfo(String orderId, Double totalPower, BigDecimal totalAmount, BigDecimal electricFee, BigDecimal serviceFee, String startTime, String endTime, String stopReason);
    //更新订单启动信息
    void updateOrderStart(String orderId, String pileId, String port, String startResult, String failReason);
    //更新订单结束
    void updateOrderStop(String pileId, String port, String stopResult, String failReason);
    //更新订单实时数据
    void handleChargingHeartBeat(String orderId, Double voltage, Double electric, String soc, Double chargePower, BigDecimal chargeFee, BigDecimal serviceFee, BigDecimal chargePrice);

    Page<ChargingOrder> queryOrderList(ChargingOrder chargingOrder);
}
