package com.hcp.system.api.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 充电桩端口对象 c_charging_port
 *
 * @author hcp
 * @date 2024-08-06
 */
@Data
@TableName("c_charging_port")
@Schema(description = "用户实体类")
public class ChargingPort extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 端口编号 */
    @TableId
    @Schema(description = "端口ID")
    private Long portId;

    /** 用户ID */
    private String userId;

    /** 插口状态 */
    @Excel(name = "插口状态")
    @Schema(description = "插口状态(N是未使用 Y 在使用 F是故障)")
    private String state;

    /** 电量 */
    @Schema(description = "电量")
    private String power;

    /** 端口名 */
    @Excel(name = "端口名")
    @Schema(description = "端口名")
    private String name;

    /** 充电桩ID */
    @Excel(name = "充电桩ID")
    @Schema(description = "充电桩ID")
    private String pileId;

    /** 端口ID */
    @Excel(name = "端口ID")
    @Schema(description = "端口设备ID")
    private String deviceId;

    /** 是否插枪 */
    @Excel(name = "是否插枪")
    @Schema(description = "是否插枪")
    private String gunInsert;

    /** 充电口状态 */
    @Excel(name = "充电口状态")
    @Schema(description = "充电口状态(0空闲1充电2预约3正在启动充电10启动失败05充电故障)")
    private Long gunStatus;

    /**  */
    @Excel(name = "")
    @Schema(description = "是否可以使用(0可用1不可用)")
    private Long canUse;

    /** 故障码 */
    @Schema(description = "故障码")
    private Long faultCode;

    /** 故障原因 */
    @Excel(name = "故障原因")
    @Schema(description = "故障原因")
    private String faultReason;

    /** 故障时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "故障时间", width = 30, dateFormat = "yyyy-MM-dd")
    @Schema(description = "故障时间")
    private Date faultTime;

    /** 二维码 */
    @Excel(name = "二维码")
    @Schema(description = "二维码")
    private String qrCode;

    /** 互联互通 */
    @Excel(name = "互联互通")
    @Schema(description = "是否为互联互通")
    private Long isHlht;

    /** 互联互通编号 */
    @Excel(name = "互联互通编号")
    @Schema(description = "互联互通编号")
    private String hlhtId;

    @TableField(exist = false)
    private Long pileType;

    @Schema(description = "站点ID")
    @TableField(exist = false)
    private Long stationId;

    @Schema(description = "充电桩运行状态，0 运行 1 离线")
    @TableField(exist = false)
    private Integer runningStatus;

    @Schema(description = "耗电量 单位 kw")
    @TableField(exist = false)
    private BigDecimal consumePower;

    @Schema(description = "充电电流")
    @TableField(exist = false)
    private String chargingCurrent;

    @Schema(description = "充电功率")
    @TableField(exist = false)
    private String chargingCdgl;

    @Schema(description = "实际充电时长")
    @TableField(exist = false)
    private String realHour;

    @Schema(description = "充电次数")
    @TableField(exist = false)
    private Long useCount;

}
