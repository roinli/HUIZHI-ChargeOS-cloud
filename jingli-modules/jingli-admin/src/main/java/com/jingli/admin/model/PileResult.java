package com.jingli.admin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName PIleResult
 * @Description: 充电桩返回结果
 * @Author 杨膑
 * @Version V1.0
 **/
@Data
@ApiModel("pileData(充电桩返回结果数据)")
public class PileResult {

    @ApiModelProperty("充电桩编号")
    private String id;

    @ApiModelProperty("小区ID")
    private String plot;

    @ApiModelProperty("小区名称")
    private String plotName;

    @ApiModelProperty("运行状态")
    private String runningStatus;

    @ApiModelProperty("充电桩名称")
    private String PKNAME;

    @ApiModelProperty("充电桩地址")
    private String PKADDRESS;

    @ApiModelProperty("使用时间")
    private Date lastUseTime;

    @ApiModelProperty(value = "使用频率")
    private Integer useCount;

    @ApiModelProperty(value = "公众号规则ID")
    private Integer wxRuleId;

    @ApiModelProperty(value = "公众号规则ID")
    private Integer cardRuleId;

    @ApiModelProperty(value = "心跳时间")
    private Integer m;

    @ApiModelProperty("充电桩类型 0:慢充 1:快充")
    private short pileType;

    @ApiModelProperty("设备状态  1:启用  0:禁用")
    private short pileStatus;

    @ApiModelProperty("设备类型 2:二轮车 4:四轮车")
    private short deviceType;
}
