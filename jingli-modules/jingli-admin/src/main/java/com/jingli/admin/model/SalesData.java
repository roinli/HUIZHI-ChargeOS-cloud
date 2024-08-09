package com.jingli.admin.model;

import com.jingli.admin.util.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @描述 销售数据
 * @创建人 yangbin
 */
@Data
public class SalesData {

    @ApiModelProperty("订单数")
    private Integer sumCount;

    @ApiModelProperty("退款订单数")
    private Integer reCount;

    @ApiModelProperty("销售总额")
    private Float sumSales;

    @ApiModelProperty("退款总额")
    private Float reSales;

    @ApiModelProperty("累计充值金额")
    private Float rechargeAmount;

    @ApiModelProperty(value = "总耗电量", hidden = true)
    private String totalPowerConsumption;

    @ApiModelProperty(value = "在线")
    private Integer onLine;

    @ApiModelProperty(value = "离线")
    private Integer offLine;


    public String getTotalPowerConsumption() {
        if (StringUtils.isBlank(totalPowerConsumption)) {
            return StringUtils.formatMoney(0);
        } else {
            return StringUtils.formatMoney(Double.valueOf(totalPowerConsumption));
        }
    }
}
