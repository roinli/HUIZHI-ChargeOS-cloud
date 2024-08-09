
package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName ChargingOrder
 * @Description: 订单表
 * @Author yangbin
 * @Version V1.0
 **/

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargingOrder {

    /**
     * 订单Id
     */
    @TableId
    private Integer id;
    /**
     * 订单类型，8001 按小时计费（取决于用户选择
     * 订单类型，8001 按小时计费（取决于用户选择,默认） 8002 按度数计费（取决于 用户选择）
     * 8003 免费订单（取决于充电桩设置）
     */
    private String orderType;
    /**
     * 耗电量 单位 kw
     */
    private BigDecimal consumePower;
    /**
     * 自定义价格ID
     */
    private String customPriceId;
    /**
     * 充电口ID
     */
    private String parklockid;
    /**
     * 充电时长
     */
    private String hour;
    /**
     * 发布金额
     */
    private String price;

    /**
     * 是否收费,2001 收费（默认），2002不收费  取决于充电桩
     */
    private String isFee;
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 充电状态 9001 未开始 9002 充电中 9003 已完成
     */
    private String chargeStatus = "9001";
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 充电桩ID
     */
    private String parkid;
    /**
     * Code
     */
    private String code;
    /**
     * 订单号
     */
    private String ordernumber;
    /**
     * 1.下单 2.取消 3.已支付 4.退款 5.完成
     */
    private String orderstate;
    /**
     * 微信openid
     */
    private String weixinOpenid;
    /**
     * 支付金额
     */
    private String ordergold;
    /**
     * wx支付订单编写
     */
    private String outTradeNo;
    /**
     * 开始时间
     */
    private String starttime;
    /**
     * 结束时间
     */
    private String endtime;

    /**
     * 设备类型 2:二轮车 4:四轮车
     */
    private Short deviceType;

    /**
     * 充电费用
     */
    private double chargeFee;

    /**
     * 服务费
     */
    private double serviceFee;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 卡号(卡支付时插入)
     */
    private String cardNo;

    /**
     * 订单来源，微信小程序：1，H5：2
     */
    private Short orderSource;

    /**
     * 充电电流
     */
    private String chargingCurrent;

    /**
     * 充电功率
     */
    private String chargingCdgl;

    /**
     * 实际充电时长
     */
    private BigDecimal realHour;

    /**
     * 实际结束时间
     */
    private String realEndTime;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;


    @TableField(exist = false)
    private String portName;

    @TableField(exist = false)
    private String portId;
}
