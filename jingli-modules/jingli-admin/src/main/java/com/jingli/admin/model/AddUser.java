package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author yangbin
 * 添加修改用户时填写数据
 */
@Data
@ApiModel("人员信息")
public class AddUser {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    @TableId(type = IdType.INPUT)
    private String useraccount;


    @ApiModelProperty(value = "真实姓名")
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    @ApiModelProperty(value = "昵称")
    @NotBlank(message = "昵称不能为空")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "是否代理商 1:是 0：否", example = "1")
    private Integer isOperator;

    @ApiModelProperty(value = "状态 1：启用 0：禁用", example = "1")
    private String state;

    @ApiModelProperty("数据权限 ：1：全部 2: 代理商本人 3：自定义分配")
    private Integer dataPermissions;

    @ApiModelProperty("客户地址")
    private String address;

    @ApiModelProperty("订购日期")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date buyDate;

    /**
     * 平台类型
     */
    private Short deviceType;
}
