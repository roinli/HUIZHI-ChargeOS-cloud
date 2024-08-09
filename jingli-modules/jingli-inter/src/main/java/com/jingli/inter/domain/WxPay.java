package com.jingli.inter.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述：微信支付bean
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxPay {
    private String appid;
    private String timeStamp;
    private String nonceStr;
    private String packages;
    private String sign;
    private String partnerid;
    private String prepayid;

}
