package com.hcp.operator.constant;

/**
 * 订单状态
 */
public interface OrderState {
    //下单
    public static final String PLACE = "1";
    //取消
    public static final String CANCEL = "2";
    //支付
    public static final String PAYED = "3";
    //退款
    public static final String REFUND = "4";
    //完成
    public static final String FINISH = "5";
    //结算中
    public static final String SETTLE = "6";
    //手动结算
    public static final String MAN_SETTLE = "7";
}
