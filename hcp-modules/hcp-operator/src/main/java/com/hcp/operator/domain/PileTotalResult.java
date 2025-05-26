package com.hcp.operator.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hcp.common.core.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PileTotalResult implements Serializable {

    @ApiModelProperty("端口状态")
    private String portStatus;

    @Excel(name = "充电桩编号")
    @ApiModelProperty("充电桩编号")
    private String chargeNo;

    //    @Excel(name = "站点编号",order = 1)
    @ApiModelProperty("站点编号")
    private String plotId;

    @Excel(name = "站点名称")
    @ApiModelProperty("站点名称")
    private String plotName;

    @Excel(name = "充电桩名称")
    @ApiModelProperty("充电桩名称")
    private String chargeName;

    //    @Excel(name = "代理商id")
    @ApiModelProperty("代理商id")
    private String userId;

    @Excel(name = "运行状态")
    @ApiModelProperty("运行状态 ")
    private String runningStatus;

    @Excel(name = "用电量")
    @ApiModelProperty("用电量")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal totalPowerConsumption;

    @Excel(name = "充电时长")
    @ApiModelProperty("充电时长")
    private String chargeTotalHour;

    @Excel(name = "服务费")
    @ApiModelProperty("服务费")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal serviceFee;

    @Excel(name = "充电费用")
    @ApiModelProperty("充电费用")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal chargeFee;

    @Excel(name = "总费用")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("总费用")
    private BigDecimal totalAmount;

    @Excel(name = "充电次数")
    @ApiModelProperty("充电次数")
    private Integer chargeTotalTimes;
}
