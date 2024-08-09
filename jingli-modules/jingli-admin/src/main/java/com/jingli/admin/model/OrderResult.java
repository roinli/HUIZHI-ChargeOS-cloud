package com.jingli.admin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @描述 向前台返回的订单数据
 * @创建人 yangbin
 */
@Data
@ApiModel("OrderResult 向前台返回的订单数据")
public class OrderResult {
    @ApiModelProperty("订单ID")
    private Integer id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("价格")
    private String price;

    @ApiModelProperty("订单状态")
    private String orderstate;

    @ApiModelProperty("开始时间")
    private String starttime;

    @ApiModelProperty("结束时间")
    private String endtime;

    @ApiModelProperty("实际结束时间")
    private String realEndTime;

    @ApiModelProperty("订单号")
    private String ordernumber;

    @ApiModelProperty("微信订单号")
    private String outTradeNo;

    @ApiModelProperty("订单类型")
    private String orderType;

    @ApiModelProperty("充电时长")
    private Float hour;

    @ApiModelProperty("充电桩编号")
    private String parkId;

    @ApiModelProperty("代理商")
    private String agentName;

    @ApiModelProperty("端口编号")
    private String deviceid;

    @ApiModelProperty("端口名称")
    private String portName;

    @ApiModelProperty("实际使用小时")
    private String realHour;

    @ApiModelProperty("卡号")
    private String cardNo;

    @ApiModelProperty("卡名字")
    private String cardName;


    @ApiModelProperty("耗电量 单位：度")
    private String consumePower;


    @ApiModelProperty("充电电流")
    private String chargingCurrent;

    @ApiModelProperty("充电功率")
    private String chargingCdgl;


    @ApiModelProperty("用户手机号")
    private String mobile;

    @ApiModelProperty("充电桩地址")
    private String parkAddress;

    @ApiModelProperty("充电桩名称")
    private String pileName;

    @ApiModelProperty("停止消息状态")
    private String messageState;

    @ApiModelProperty("充电状态")
    private String chargeStatus;

    @ApiModelProperty("totalPrice")
    private String totalPrice;

    @ApiModelProperty("订单金额")
    private String orderGold;

    @ApiModelProperty("预计费用")
    private String estimateAmount;

    @ApiModelProperty("用户openId")
    private String openId;

    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("充电桩端口ID")
    private String parklockId;

    @ApiModelProperty("是否收费，2001 收费（默认），2002不收费")
    private String isFee;

    @ApiModelProperty("payTime")
    private Date payTime;

    @ApiModelProperty("创建时间")
    private String createTime;
}
