package com.hcp.system.api.domain.Bo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class FeeRangeTime {
    /**
     * 价格类型
     */
    private byte priceType;

    /**
     * 服务费
     */
    private BigDecimal servicePrice;

    /**
     * 开始时间
     */
    private float startTime;

    /**
     * 结束时间
     */
    private float endTime;
}
