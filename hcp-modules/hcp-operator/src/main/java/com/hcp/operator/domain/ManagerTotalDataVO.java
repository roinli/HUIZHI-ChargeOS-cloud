package com.hcp.operator.domain;

import com.hcp.common.core.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ManagerTotalDataVO {


    @ApiModelProperty("总收益")
    @Excel(name = "总收益")
    private BigDecimal sumSales;

    @Excel(name = "实际收益")
    @ApiModelProperty("实际收益")
    private BigDecimal realSales;

    @Excel(name = "订单退款")
    @ApiModelProperty("订单退款")
    private BigDecimal reSales;

    @Excel(name = "电费")
    @ApiModelProperty("电费")
    private BigDecimal chargeFee;

    @Excel(name = "服务费")
    @ApiModelProperty("服务费")
    private BigDecimal serviceFee;

    @Excel(name = "订单数量")
    @ApiModelProperty("订单数量")
    private Integer sumCount;

    @Excel(name = "充电次数")
    @ApiModelProperty("充电次数")
    private Integer chargeTimes;

    @Excel(name = "充电时长")
    @ApiModelProperty("充电时长")
    private String realHour;

    @Excel(name = "总耗电量")
    @ApiModelProperty(name = "总耗电量")
    private BigDecimal consumePower;

    @Excel(name = "设备总数")
    @ApiModelProperty(name = "设备总数")
    private Integer totalNumbers;

    @Excel(name = "运行设备")
    @ApiModelProperty(name = "在线")
    private Integer onLine;

    @Excel(name = "离线设备")
    @ApiModelProperty(name = "离线")
    private Integer offLine;
}
