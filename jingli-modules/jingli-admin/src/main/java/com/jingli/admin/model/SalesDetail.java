package com.jingli.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @描述 销售统计每日详情数据
 * @创建人 yangbin
 */
@Data
public class SalesDetail extends SalesData {

    @ApiModelProperty("订单时间")
    private String createTime;

    @ApiModelProperty("有效订单数")
    private Integer validCount;
}
