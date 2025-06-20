package com.hcp.simulator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "端口充电状态信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargeInfoDTO {

    @Schema(description = "充电桩ID")
    private Long pileId;

    @Schema(description = "端口-设备ID")
    private String deviceId;

    @Schema(description = "充电口状态0空闲1充电2预约3正在启动充电10启动失败05充电故障")
    private Long gunStatus;

    @Schema(description = "电压")
    private Double voltage;

    @Schema(description = "电流")
    private Double electric;

    @Schema(description = "soc")
    private Float soc;

    @Schema(description = "充电功率")
    private Float chargePower;

    @Schema(description = "充电价格")
    private BigDecimal chargePrice;

    @Schema(description = "电费")
    private BigDecimal chargeFee;

    @Schema(description = "服务费")
    private BigDecimal serviceFee;

    @Schema(description = "开始时间")
    private Date startTime;

    @Schema(description = "结束时间")
    private Date endTime;

}
