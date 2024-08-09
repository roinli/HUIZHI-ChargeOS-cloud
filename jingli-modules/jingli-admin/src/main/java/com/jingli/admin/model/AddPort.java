package com.jingli.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @描述 端口添加数据
 * @创建人 yangbin
 */
@Data
public class AddPort {

    @ApiModelProperty(value = "充电桩编号id")
    @NotBlank(message = "不能为空")
    private String parkid;

    @ApiModelProperty(value = "端口名称")
    @NotBlank(message = "不能为空")
    private String name;

    @ApiModelProperty(value = "端口编号")
    private String deviceid;

    @ApiModelProperty(value = "端口状态 N是空闲 Y 在使用")
    @NotBlank(message = "不能为空")
    private String state;
}
