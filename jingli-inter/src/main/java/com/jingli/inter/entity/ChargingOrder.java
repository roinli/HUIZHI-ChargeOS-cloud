package com.jingli.inter.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 充电订单表;
 *
 * @author : Abdulla
 * @date : 2023-8-25
 */
@ApiModel(value = "充电订单表", description = "")
@Data
public class ChargingOrder  implements Serializable, Cloneable {
    /**
     *
     */
    @ApiModelProperty(name = "orderId", notes = "")
    private Integer id;
    /**
     * 微信openid
     */
    @ApiModelProperty(name = "微信openid", notes = "")
    private String weixinOpenid;
    /**
     * 订单状态 1.下单 2.取消 3.支付成功 4.退款 5.完成 6.结算中 7.手动结算
     */
    @ApiModelProperty(name = "订单状态 1.下单 2.取消 3.支付成功 4.退款 5.完成 6.结算中 7.手动结算", notes = "")
    private String orderstate;
    /**
     * 充电桩ID
     */
    @ApiModelProperty(name = "充电桩ID", notes = "")
    private String parkid;
    /**
     * 充电口ID
     */
    @ApiModelProperty(name = "充电口ID", notes = "")
    private String parklockid;
    /**
     * 开始时间
     */
    @ApiModelProperty(name = "开始时间", notes = "")
    private String starttime;
    /**
     * 结束时间
     */
    @ApiModelProperty(name = "结束时间", notes = "")
    private String endtime;
    /**
     * 订单号
     */
    @ApiModelProperty(name = "订单号", notes = "")
    private String ordernumber;
    /**
     * 充电状态 9001 未开始 9002 充电中 9003 已完成
     */
    @ApiModelProperty(name = "充电状态 9001 未开始 9002 充电中 9003 已完成", notes = "")
    private String chargeStatus;
    /**
     * 是否收费,2001 收费（默认），2002不收费  取决于充电桩
     */
    @ApiModelProperty(name = "是否收费,2001 收费（默认），2002不收费  取决于充电桩", notes = "")
    private String isFee;
    /**
     * 耗电量 单位 kw
     */
    @ApiModelProperty(name = "耗电量 单位 kw", notes = "")
    private Double consumePower;
    /**
     * 充电电流
     */
    @ApiModelProperty(name = "充电电流", notes = "")
    private String chargingCurrent;
    /**
     * 充电功率
     */
    @ApiModelProperty(name = "充电功率", notes = "")
    private String chargingCdgl;
    /**
     * 自定义价格ID
     */
    @ApiModelProperty(name = "自定义价格ID", notes = "")
    private String customPriceId;
    /**
     * 充电时长，用户选择充电时间
     */
    @ApiModelProperty(name = "充电时长，用户选择充电时间", notes = "")
    private String hour;
    /**
     * 发布金额
     */
    @ApiModelProperty(name = "发布金额", notes = "")
    private String price;
    /**
     * 手机号
     */
    @ApiModelProperty(name = "手机号", notes = "")
    private String mobile;
    /**
     * 订单创建时间
     */
    @ApiModelProperty(name = "订单创建时间", notes = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    /**
     *
     */
    @ApiModelProperty(name = "code", notes = "")
    private String code;
    /**
     * 卡号(卡支付时插入)
     */
    @ApiModelProperty(name = "卡号(卡支付时插入)", notes = "")
    private String cardNo;
    /**
     * 支付金额
     */
    @ApiModelProperty(name = "支付金额", notes = "")
    private String ordergold;
    /**
     * 订单类型，8001 按小时计费（取决于用户选择,默认） 8002 按度数计费（取决于 用户选择）
     */
    @ApiModelProperty(name = "订单类型，8001 按小时计费（取决于用户选择,默认） 8002 按度数计费（取决于 用户选择）", notes = "")
    private String orderType;
    /**
     * wx支付订单编写
     */
    @ApiModelProperty(name = "wx支付订单编写", notes = "")
    private String outTradeNo;
    /**
     * 实际充电时长
     */
    @ApiModelProperty(name = "实际充电时长", notes = "")
    private String realHour;
    /**
     * 实际充电结束时间
     */
    @ApiModelProperty(name = "实际充电结束时间", notes = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date realEndTime;
    /**
     * 退款金额
     */
    @ApiModelProperty(name = "退款金额", notes = "")
    private Double refundAmount;
    /**
     * 设备类型 2:二轮车 4:四轮车
     */
    @ApiModelProperty(name = "设备类型 2:二轮车 4:四轮车", notes = "")
    private Integer deviceType;
    /**
     * 充电费用
     */
    @ApiModelProperty(name = "充电费用", notes = "")
    private Float chargeFee;
    /**
     * 服务费
     */
    @ApiModelProperty(name = "服务费", notes = "")
    private Float serviceFee;
    /**
     * 支付时间
     */
    @ApiModelProperty(name = "支付时间", notes = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;
    /**
     * 订单来源，微信小程序：1，H5：2  卡：3
     */
    @ApiModelProperty(name = "订单来源，微信小程序：1，H5：2  卡：3", notes = "")
    private Integer orderSource;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户手机号
     */
    private String userTelephoneNo;

    /**
     * 充电桩编号
     */
    private long pileNo;

    /**
     * 充电桩名称
     */
    private String pileName;

    /**
     * 地址
     */
    private String address;

    /**
     * 充电桩类型 0:慢充 1:快充
     */
    private Short pileType;

    /**
     * 充电端口名
     */
    private String pilePortName;

    private String electricity;
    private String voltage;
    private String maxPower;

    /**
     * 电站名称
     */
    private String plotName;


    /**
     * 充电端口名
     */
    private String chargingPilePort;

    /**
     * 枪编号 01 或者 02
     */
    private String devicePortNum;

    // 端口名字
    private String portName;


}