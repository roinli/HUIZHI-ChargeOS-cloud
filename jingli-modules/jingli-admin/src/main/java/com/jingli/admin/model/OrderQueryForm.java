package com.jingli.admin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName OrderQueryForm
 * @Description: 订单查询类
 * @Author 杨膑
 * @Version V1.0
 **/
@Getter
@Setter
@ApiModel(value = "订单查询条件")
public class OrderQueryForm {

    @ApiModelProperty(value = "订单状态")
    private String state;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "场地（小区编号）")
    private String plot;

    @ApiModelProperty(value = "设备")
    private String parkid;

    @ApiModelProperty(value = "卡号")
    private String cardNo;

    @ApiModelProperty(value = "订单号")
    private String ordernumber;

    /*代理商ID*/
    @ApiModelProperty(hidden = true)
    private String userId;
}
