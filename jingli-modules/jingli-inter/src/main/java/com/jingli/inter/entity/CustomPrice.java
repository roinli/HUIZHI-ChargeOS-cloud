package com.jingli.inter.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 价格表;
 *
 * @author : Abdulla
 * @date : 2023-8-25
 */
@ApiModel(value = "价格表", description = "")
@Data
public class CustomPrice implements Serializable, Cloneable {
    /**
     *
     */
    @ApiModelProperty(name = "CustomPriceId", notes = "")
    private Integer id;
    /**
     * 计费类型 3001：按小时计费 3002：按度数计费（此类型，一个充电桩就一个）
     */
    @ApiModelProperty(name = "计费类型 3001：按小时计费 3002：按度数计费（此类型，一个充电桩就一个）", notes = "")
    private String chargeType;
    /**
     * 时长
     */
    @ApiModelProperty(name = "时长", notes = "")
    private String time;
    /**
     * 费用，四轮车时是电费 元/度
     */
    @ApiModelProperty(name = "费用，四轮车时是电费 元/度", notes = "")
    private Double price;
    /**
     * 每小时-单价
     */
    @ApiModelProperty(name = "每小时-单价", notes = "")
    private String hours;
    /**
     * 1.固定 2.自定义时长
     */
    @ApiModelProperty(name = "1.固定 2.自定义时长", notes = "")
    private String type;
    /**
     * 规则大于4个小时，第五个小时0.5元
     */
    @ApiModelProperty(name = "规则大于4个小时，第五个小时0.5元", notes = "")
    private String granternum;
    /**
     * 规则大于4个小时，第五个小时0.5元
     */
    @ApiModelProperty(name = "规则大于4个小时，第五个小时0.5元", notes = "")
    private String granterprice;
    /**
     * 充电桩ID
     */
    @ApiModelProperty(name = "充电桩ID", notes = "")
    private String chargePileId;
    /**
     * 规则ID
     */
    @ApiModelProperty(name = "规则ID", notes = "")
    private String ruleId;
    /**
     * 删除标记，默认0：未删除，1 删除
     */
    @ApiModelProperty(name = "删除标记，默认0：未删除，1 删除", notes = "")
    private Integer delFlag;
    /**
     * 服务费用，元/度
     */
    @ApiModelProperty(name = "服务费用，元/度", notes = "")
    private BigDecimal servicePrice;
    /**
     * 最小瓦数
     */
    @ApiModelProperty(name = "最小瓦数", notes = "")
    private Integer minWatt;
    /**
     * 最大瓦数
     */
    @ApiModelProperty(name = "最大瓦数", notes = "")
    private Integer maxWatt;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间", notes = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    /**
     * 更新时间
     */
    @ApiModelProperty(name = "更新时间", notes = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 电费类型(4轮车使用) 0:尖 1:峰 2:平 3:谷
     */
    @ApiModelProperty(name = "电费类型(4轮车使用) 0:尖 1:峰 2:平 3:谷", notes = "")
    private Integer priceType;
    /**
     * 时间范围,一个json字符串 [{"startTime":0.5,"endTime":1}]
     */
    @ApiModelProperty(name = "时间范围,一个json字符串 ", notes = "")
    private String rangTime;


}