package com.hcp.operator.constant;

/**
 * 充电状态
 */
public interface ChargeStatus {
    /**
     * 未开始
     */
    public static final String PRE_CHARGE = "9001";
    /**
     * 充电中
     */
    public static final String CHARGING = "9002";
    /**
     * 已完成
     */
    public static final String FINISH_CHARGE = "9003";
}
