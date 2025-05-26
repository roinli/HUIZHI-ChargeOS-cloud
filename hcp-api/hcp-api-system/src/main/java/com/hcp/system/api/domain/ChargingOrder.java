package com.hcp.system.api.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 充电订单对象 c_charging_order
 *
 * @author hcp
 * @date 2024-08-06
 */
@Data
@TableName("c_charging_order")
@Schema(description = "充电订单")
public class ChargingOrder extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    @TableId
    @Schema(description = "订单id")
    private String orderId;

    /** 用户id */
    @Excel(name = "用户id")
    @Schema(description = "用户id")
    private Long userId;

    /** 订单状态 */
    @Excel(name = "订单状态")
    @Schema(description = "订单状态( 1.下单 2.取消 3.支付成功 4.退款 5.完成 6.结算中 7.手动结算)")
    private String orderState;

    /** 充电桩ID */
    @Excel(name = "充电桩ID")
    @Schema(description = "充电桩ID")
    private String pileId;

    /** 充电口ID */
    @Excel(name = "充电口ID")
    @Schema(description = "充电口ID")
    private Long portId;

    /** 开始时间 */
    @Excel(name = "开始时间")
    @Schema(description = "开始时间")
    private String startTime;

    /** 结束时间 */
    @Excel(name = "结束时间")
    @Schema(description = "结束时间")
    private String endTime;

    /** 订单号 */
    @Schema(description = "订单号")
    private String orderNumber;

    /** 充电状态 */
    @Excel(name = "充电状态")
    @Schema(description = "充电状态(充电状态 9001 未开始 9002 充电中 9003 已完成)")
    private String chargeStatus;

    /** 是否收费 */
    @Excel(name = "是否收费")
    @Schema(description = "是否收费")
    private String isFee;

    /** 耗电量 */
    @Excel(name = "耗电量")
    @Schema(description = "耗电量")
    private BigDecimal consumePower;

    /** 充电电流 */
    @Excel(name = "充电电流")
    @Schema(description = "充电电流")
    private String chargingCurrent;

    /** 充电功率 */
    @Excel(name = "充电功率")
    @Schema(description = "充电功率")
    private String chargingCdgl;

    /** 自定义价格 */
    private String customPriceId;

    /** 充电时长，用户选择充电时间 */
    @Schema(description = "充电时长")
    private String hour;

    /** 发布金额 */
    @Excel(name = "发布金额")
    private String price;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** $column.columnComment */
    private String code;

    /** 卡号(卡支付时插入) */
    @Excel(name = "卡号(卡支付时插入)")
    private String cardNo;

    /** 支付金额 */
    @Excel(name = "支付金额")
    @Schema(description = "支付金额")
    private String ordergold;

    /** 订单类型 */
    @Schema(description = "订单类型(8001 按小时计费（取决于用户选择,默认） 8002 按度数计费（取决于 用户选择）)")
    private String orderType;

    /** 支付订单 */
    @Schema(description = "支付订单")
    private String outTradeNo;

    /** 实际充电时长 */
    @Excel(name = "实际充电时长")
    @Schema(description = "实际充电时长")
    private String realHour;

    /** 实际充电结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际充电结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    @Schema(description = "实际充电结束时间")
    private Date realEndTime;

    /** 退款金额 */
    @Schema(description = "退款金额")
    private BigDecimal refundAmount;

    /** 设备类型 */
    @Excel(name = "设备类型")
    @Schema(description = "设备类型 (2:二轮车 4:四轮车)")
    private Long deviceType;

    /** 充电费用 */
    @Excel(name = "充电费用")
    @Schema(description = "充电费用")
    private BigDecimal chargeFee;

    /** 服务费 */
    @Excel(name = "服务费")
    @Schema(description = "服务费")
    private BigDecimal serviceFee;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    @Schema(description = "支付时间")
    private Date payTime;

    /** 订单来源 */
    @Excel(name = "订单来源")
    @Schema(description = "订单来源")
    private Long orderSource;

    /** 团体充电 */
    @Excel(name = "团体充电")
    private Long isGroupOrder;

    /** 团体卡号 */
    @Excel(name = "团体卡号")
    private String groupCardNo;

    /** 发票主键 */
    private Long invoiceId;

    /** 发票号 */
    private String invoiceNo;

    /** 停止原因 */
    @Schema(description = "停止原因")
    private String stopReason;

    @Excel(name = "站点名")
    @TableField(exist = false)
    @Schema(description = "站点名")
    private String stationName;

    @Excel(name = "枪口名")
    @TableField(exist = false)
    @Schema(description = "枪口名")
    private String portName;

    @Excel(name = "支付类型")
    @TableField(exist = false)
    private String payType;

    @Excel(name = "订单类型")
    @TableField(exist = false)
    private String chargingOrderType;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String pileName;

    @TableField(exist = false)
    private String address;

    @TableField(exist = false)
    private Long pileType;

    @TableField(exist = false)
    private String electricity;

    @TableField(exist = false)
    private String voltage;

    @TableField(exist = false)
    private String maxPower;

    @TableField(exist = false)
    private List<OrderLog> logList;

    @TableField(exist = false)
    private Integer pageNo;

    @TableField(exist = false)
    private Integer pageSize;

    @TableField(exist = false)
    private String deviceId;


}
