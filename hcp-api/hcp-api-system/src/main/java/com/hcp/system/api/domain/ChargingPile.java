package com.hcp.system.api.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 充电桩对象 c_charging_pile
 *
 * @author hcp
 * @date 2024-08-06
 */
@Data
@TableName("c_charging_pile")
public class ChargingPile extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    @TableId(type = IdType.INPUT)
    private String pileId;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 收费类型 */
    @Excel(name = "收费类型")
    private String cpFeeType;

    /** sim卡序列号 */
    private String simCard;

    /** sim卡到期时间 */
    private String simDueTime;

    /** 设备温度 */
    private BigDecimal equipTemperature;

    /** 设备cpu温度 */
    private BigDecimal equipCpuTemperature;

    /** 经度 */
    private String lat;

    /** 纬度 */
    private String lng;

    /** 地址 */
    private String address;

    /** 图片地址 */
    private String imgUrl;

    /** 运行状态 */
    @Excel(name = "运行状态")
    private Long runningStatus;

    /** 离线时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "离线时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date offlineTime;

    /** 代理商ID */
    @Excel(name = "代理商ID")
    private Long userId;

    /** 最后一次使用时间 */
    private Date lastUseTime;

    /** 站点ID */
    private Long stationId;

    /** 微信公众号支付规则ID */
    private Long wxRuleId;

    /** 卡规则id */
    private Long cardRuleId;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private Long deviceType;

    /** 充电桩类型 */
    @Excel(name = "充电桩类型")
    private Long pileType;

    /** 设备状态 */
    @Excel(name = "设备状态")
    private Integer pileStatus;

    /** 电流信息 */
    @Excel(name = "电流信息")
    private String electricity;

    /** 电压信息 */
    @Excel(name = "电压信息")
    private String voltage;

    /** 最大功率 */
    @Excel(name = "最大功率")
    private Long maxPower;

    /** 更新收费规则 */
    @Excel(name = "更新收费规则")
    private Integer feeNeedUpdate;

    /** 协议版本 */
    private Long apiVersion;

    /** 删除状态 0：未删除，1：删除 */
    private Integer deleted;

    /** 互联互通设备 */
    private Long isHlht;

    /** 互联互通编号 */
    private String hlhtId;



}
