
package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Card
 * @Description:
 * @Author yangbin
 * @Version V1.0
 **/

@Getter
@Setter

@ApiModel(value = "card对象", description = "卡管理")
@TableName("c_card")
public class Card {

    @ApiModelProperty(value = "主键id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "卡号", name = "card_no", example = "20190001")
    @TableId(value = "card_no", type = IdType.INPUT)
    @NotBlank(message = "卡号不能为空")
//    @Length(min = 5,max = 16,message = "卡号长度在5到16之间")
    private String cardNo;

    @ApiModelProperty(value = "微信open_id", name = "openId", example = "20190001", hidden = true)
    private String openId;


    @ApiModelProperty(value = "绑定的用户手机号", name = "mobile", example = "15801448911")
    @NotBlank(message = "手机号不能为空")
    private String mobile;
    /**
     * 余额
     */
    @ApiModelProperty("余额")
    private BigDecimal sum;
    /**
     * 运营商
     */
    @ApiModelProperty(hidden = true)
    private String operator;

    @ApiModelProperty("用户名")
    @TableField(exist = false)
    private String userName;
    /**
     * 卡状态 0：正常 1：挂失 2：未启用
     */
    @ApiModelProperty(hidden = true)
    private String state;

    @ApiModelProperty("设备类型 2:二轮车 4:四轮车")
    private Short deviceType = 4;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty(value = "用户绑定时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bindTime;

    @ApiModelProperty("姓名")
    @TableField(exist = false)
    private String realName;

    @ApiModelProperty("物理卡号")
    private String physicalCardNo;

}
