package com.hcp.simulator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 充电订单对象 c_charging_order
 *
 * @author hcp
 * @date 2024-08-06
 */
@Data
@Schema(description = "用户实体类")
public class ChargingOrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "订单编号")
    private String orderId;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "充电桩ID")
    private String pileId;

    @Schema(description = "端口-设备ID")
    private String deviceId;

}
