package com.hcp.operator.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName PriceVo
 * @Description 价格数据
 * @Author liangbo
 * @Date 2023/2/18 16:20
 * @Version 1.0
 **/
@Data
@ToString
public class PriceVo {

    /**
     * 价格id
     */
    @ApiModelProperty("价格id")
    private Integer id;

    /**
     * 电费类型(4轮车使用) 0:尖 1:峰 2:平 3:谷
     */
//    @Range(min = 0,max = 3,message = "电费类型参数不对")
    @ApiModelProperty("电费类型(4轮车使用) 0:尖 1:峰 2:平 3:谷")
    private byte priceType;

    @DecimalMin(value = "0.1",message = "电费单价最小是0.1元/度")
    @DecimalMax(value = "1000",message = "电费单价最大是1000元/度")
    @ApiModelProperty("电费单价")
    private BigDecimal price;

    @DecimalMin(value = "0",message = "服务费单价最小是0元/度")
    @DecimalMax(value = "1000",message = "服务费单价最大是1000元/度")
    @ApiModelProperty("服务费单价")
    private BigDecimal servicePrice;

    /**
     * 时间段范围
     */
    @Valid
    @ApiModelProperty("电价时间范围")
    private List<TimeRange> timeRangeList;


    @Data
    public static class TimeRange {

        @DecimalMin(value = "0",message = "开始时间，最小是00:00")
        @DecimalMax(value = "23.5",message = "开始时间，最大是23:30")
        @ApiModelProperty("开始时间")
        private float startTime;

        @DecimalMin(value = "0.5",message = "结束时间，最小是00:30")
        @DecimalMax(value = "24",message = "结束时间，最大是24:00")
        @ApiModelProperty("结束时间")
        private float endTime;
    }
}
