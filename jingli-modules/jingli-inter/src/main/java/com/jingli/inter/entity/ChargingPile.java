package com.jingli.inter.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 充电桩表;
 *
 * @author : Abdulla
 * @date : 2023-8-25
 */
@ApiModel(value = "充电桩表", description = "")
@Data
public class ChargingPile extends BaseDO implements Serializable, Cloneable {
    /**
     * 编号
     */
    @ApiModelProperty(name = "编号", notes = "")
    private Integer id;
    /**
     * 充电桩名称
     */
    @ApiModelProperty(name = "充电桩名称", notes = "")
    private String pkname;
    /**
     * 默认 2001 充电桩收费类型，2001：收费 2002：免费
     */
    @ApiModelProperty(name = "默认 2001 充电桩收费类型，2001：收费 2002：免费", notes = "")
    private String cpFeeType;
    /**
     * sim卡序列号
     */
    @ApiModelProperty(name = "sim卡序列号", notes = "")
    private String simCard;
    /**
     * sim卡到期时间
     */
    @ApiModelProperty(name = "sim卡到期时间", notes = "")
    private String simDueTime;
    /**
     * 设备温度
     */
    @ApiModelProperty(name = "设备温度", notes = "")
    private Double equipTemperature;
    /**
     * 设备cpu温度
     */
    @ApiModelProperty(name = "设备cpu温度", notes = "")
    private Double equipCpuTemperature;
    /**
     * 经度
     */
    @ApiModelProperty(name = "经度", notes = "")
    private String lat;
    /**
     * 纬度
     */
    @ApiModelProperty(name = "纬度", notes = "")
    private String lng;
    /**
     * 地址
     */
    @ApiModelProperty(name = "地址", notes = "")
    private String pkaddress;
    /**
     * 图片地址
     */
    @ApiModelProperty(name = "图片地址", notes = "")
    private String imgUrl;
    /**
     * 运行状态，0 运行 1 离线
     */
    @ApiModelProperty(name = "运行状态，0 运行 1 离线", notes = "")
    private Integer runningStatus;
    /**
     * 代理商ID
     */
    @ApiModelProperty(name = "代理商ID", notes = "")
    private String userid;
    /**
     * 最后一次使用时间
     */
    @ApiModelProperty(name = "最后一次使用时间", notes = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUseTime;
    /**
     * 小区表id
     */
    @ApiModelProperty(name = "小区表id", notes = "")
    private String plot;
    /**
     * 微信公众号支付规则ID
     */
    @ApiModelProperty(name = "微信公众号支付规则ID", notes = "")
    private Integer wxRuleId;
    /**
     * 卡规则id
     */
    @ApiModelProperty(name = "卡规则id", notes = "")
    private Integer cardRuleId;

    /**
     * 设备类型 2:二轮车 4:四轮车
     */
    @ApiModelProperty(name = "设备类型 2:二轮车 4:四轮车", notes = "")
    private Integer deviceType;
    /**
     * 充电桩类型 0:慢充 1:快充
     */
    @ApiModelProperty(name = "充电桩类型 0:慢充 1:快充", notes = "")
    private Integer pileType;
    /**
     * 设备状态  1:启用  0:禁用
     */
    @ApiModelProperty(name = "设备状态  1:启用  0:禁用", notes = "")
    private Integer pileStatus;
    /**
     * 电流信息
     */
    @ApiModelProperty(name = "电流信息", notes = "")
    private String electricity;
    /**
     * 电压信息
     */
    @ApiModelProperty(name = "电压信息", notes = "")
    private String voltage;
    /**
     * 最大功率
     */
    @ApiModelProperty(name = "最大功率", notes = "")
    private Integer maxPower;
    /**
     * 费用需要更新，1:需要更新 0:不需要
     */
    @ApiModelProperty(name = "费用需要更新，1:需要更新 0:不需要", notes = "")
    private Integer feeNeedUpdate;


}