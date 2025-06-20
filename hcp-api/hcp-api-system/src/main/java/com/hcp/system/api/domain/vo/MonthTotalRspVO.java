package com.hcp.system.api.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: com.jingli.chargeapi.vo.resp.MonthTotalRspVO
 * @author: lv lu
 * @create: 2023-11-14 1:04
 * @description: TODO
 */
@Data
public class MonthTotalRspVO {
    @ApiModelProperty("充电度数")
    private String chargeDegree;

    @ApiModelProperty("充电金额")
    private String chargeAmount;

    @ApiModelProperty("充电时长")
    private String chargeTime;
}
