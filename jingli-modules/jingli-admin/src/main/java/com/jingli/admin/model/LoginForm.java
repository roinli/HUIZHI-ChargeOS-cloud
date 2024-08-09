package com.jingli.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName LoginForm
 * @Description:
 * @Author 杨膑
 * @Version V1.0
 **/
@Getter
@Setter
public class LoginForm {
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 平台类型
     */
    @ApiModelProperty(value = "平台类型")
    private Short platForm;

}

