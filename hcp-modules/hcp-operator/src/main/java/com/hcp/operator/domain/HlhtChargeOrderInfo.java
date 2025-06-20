package com.hcp.operator.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 充电站充电订单信息对象 hlht_charge_order_info
 *
 * @author hcp
 * @date 2024-08-11
 */
@Data
@TableName("hlht_charge_order_info")
public class HlhtChargeOrderInfo extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 充电订单号 */
    @TableId
    private String orderNo;

    /** 充电接口唯一 标识 */
    @Excel(name = "充电接口唯一 标识")
    private String connectorId;

    /** 运营商ID */
    @Excel(name = "运营商ID")
    private String operatorId;

    /** 充电站ID */
    @Excel(name = "充电站ID")
    private String stationId;

    /** 充电设备ID */
    @Excel(name = "充电设备ID")
    private String equipmentId;

    /** 设备所属方ID */
    @Excel(name = "设备所属方ID")
    private String equipmentOwnerId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 车辆唯一识别码 */
    @Excel(name = "车辆唯一识别码")
    private String vin;

    /** 充电开始SOC */
    @Excel(name = "充电开始SOC")
    private BigDecimal startSoc;

    /** 充电结束SOC */
    @Excel(name = "充电结束SOC")
    private BigDecimal endSoc;

    /** 本单开始充电 时间 */
    @Excel(name = "本单开始充电 时间")
    private String startTime;

    /** 结束充电时间 */
    @Excel(name = "结束充电时间")
    private String endTime;

    /** 尖阶段电量 */
    @Excel(name = "尖阶段电量")
    private BigDecimal cuspElect;

    /** 峰阶段电量 */
    @Excel(name = "峰阶段电量")
    private BigDecimal peakElect;

    /** 平阶段电量 */
    @Excel(name = "平阶段电量")
    private BigDecimal flatElect;

    /** 谷阶段电量 */
    @Excel(name = "谷阶段电量")
    private BigDecimal valleyElect;

    /** 推送时间 */
    @Excel(name = "推送时间")
    private String pushTimeStamp;

    /** 总电费 */
    @Excel(name = "总电费")
    private BigDecimal totalElecMoney;

    /** 总服务费 */
    @Excel(name = "总服务费")
    private BigDecimal totalSeviceMoney;

    /** 累计总金额 */
    @Excel(name = "累计总金额")
    private BigDecimal totalMoney;

    /** 充电结束原因 */
    @Excel(name = "充电结束原因")
    private Long stopReason;

    /** 充电结束原因描述 */
    @Excel(name = "充电结束原因描述")
    private String stopDesc;

    /** 时段数 */
    @Excel(name = "时段数")
    private Long sumPeriod;

    /** 充电明细信息列表 */
    @Excel(name = "充电明细信息列表")
    private String chargeDetails;

    /** 总耗电量 */
    @Excel(name = "总耗电量")
    private BigDecimal totalPower;

    /** 订单号 */
    @Excel(name = "订单号")
    private String ordernumber;



}
