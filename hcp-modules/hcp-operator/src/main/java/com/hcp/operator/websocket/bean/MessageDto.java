package com.hcp.operator.websocket.bean;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    /**
     * 充电状态，1是充电中 2订单取消了
     */
    private Short statusCode = 1;

    /**
     * 已充电量
     */
    private BigDecimal hasChargePower;

    /**
     * 实时功率
     */
    private double realTimePower;

    /**
     * 实时电流
     */
    private  float electricity;

    /**
     * 实时电压
     */
    private float voltage;

    /**
     * 服务费用
     */
    private BigDecimal serviceFee;

    /**
     * 充电费用
     */
    private BigDecimal powerFee;

    /**
     * 总费用
     */
    private BigDecimal totalFee;

    /**
     * 累计充电时间，单位：分钟
     */
    private Integer chargeMin;

    /**
     * 估算剩余充电时间,单位：分钟
     */
    private Integer preEndMin;

    /**
     * 充电量
     */
    private Integer soc;

    /**
     * 开始时间
     */
    private String startTime;

    private String portName;
}
