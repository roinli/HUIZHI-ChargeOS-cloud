package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName ChargingPile
 * @Description:
 * @Author yangbin
 * @Version V1.0
 **/

@Data
@ApiModel(value = "chargingPile充电桩对象", description = "chargingPile充电桩对象......")
public class ChargingPile extends AddPile {

    /**
     * 默认 2001 充电桩收费类型，2001：收费 2002：免费
     */
    @ApiModelProperty(value = "充电桩收费类型 2001：收费 2002：免费")
    private String cpFeeType;

    /**
     * sim卡序列号
     */
    @ApiModelProperty(value = "sim卡序列号")
    private String simCard;

    /**
     * sim卡到期时间
     */
    @ApiModelProperty(value = "sim卡序列号")
    private String simDueTime;

    /**
     * 设备温度
     */

    @ApiModelProperty(value = "设备温度")
    private BigDecimal equipTemperature;

    /**
     * 设备cpu温度
     */
    @ApiModelProperty(value = "cpu温度")
    private BigDecimal equipCpuTemperature;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String lat;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private String lng;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String pkaddress;

    /**
     * 充电桩图片地址
     */
    @ApiModelProperty(value = "充电桩图片地址")
    private String imgUrl;

    /**
     * 运行状态，0 运行 1 离线
     */
    @ApiModelProperty(value = "运行状态", name = "runningStatus", example = "0\1")
    private String runningStatus;

    @ApiModelProperty(value = "代理商ID", name = "userId")
    @TableField("userId")
    private String userId;

    @ApiModelProperty(value = "最后一次使用时间")
    private Date lastUseTime;

    @ApiModelProperty(value = "公众号规则ID")
    private String wxRuleId;

    @ApiModelProperty(value = "卡规则ID")
    private String cardRuleId;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

}
