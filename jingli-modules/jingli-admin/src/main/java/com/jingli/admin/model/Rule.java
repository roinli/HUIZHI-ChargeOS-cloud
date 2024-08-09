
package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ClassName Rule
 * @Description:
 * @Author yangbin
 * @Version V1.0
 **/

@Getter
@Setter
public class Rule {

    /**
     * Id
     */
    @TableId
    @ApiModelProperty("规则id")
    private Integer id;
    /**
     * 规则名称
     */
    @ApiModelProperty("规则名称")
    @NotBlank(message = "不能为空")
    private String changeName;
    /**
     * 计费类型 3001：按小时计费 3002：按度数计费（此类型，一个充电桩就一个）
     */
    @ApiModelProperty("计费类型(见代码表parentCode=3)")
    @NotBlank(message = "不能为空")
    private String chargeType;

    @ApiModelProperty("计费类型名称")
    @TableField(exist = false)
    private String chargeTypeName;
    /**
     * 规则类型 10001 卡 10002公众号
     */
    @ApiModelProperty("规则类型(见代码表parentCode=10)")
    @NotBlank(message = "不能为空")
    private String ruleType;

    @ApiModelProperty("规则类型名称")
    @TableField(exist = false)
    private String ruleTypeName;
    /**
     * 代理商ID
     */
    @ApiModelProperty("代理商ID")
    private Integer userId;
    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @ApiModelProperty("代理商昵称")
    @TableField(exist = false)
    private String userNm;

    @ApiModelProperty("代理商真实姓名")
    @TableField(exist = false)
    private String realName;

    @ApiModelProperty("使用的充电桩数量")
    @TableField(exist = false)
    private Integer pileCount;

    @ApiModelProperty("设备类型 2:二轮车 4:四轮车")
    private Short deviceType;

    @ApiModelProperty(value = "是否为平台默认规则 1:是 0:否")
    private Integer isSysGive;

    @ApiModelProperty(value = "是否为代理商默认规则 1:是 0:否")
    private Integer isGive;

    @ApiModelProperty(hidden = true)
    private Date updateTime;
}
