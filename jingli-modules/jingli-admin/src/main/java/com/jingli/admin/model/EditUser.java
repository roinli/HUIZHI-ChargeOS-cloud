package com.jingli.admin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yangbin
 * 修改用户数据
 */
@Data
@ApiModel("修改数据")
public class EditUser extends AddUser {

    @ApiModelProperty(value = "id")
    private Integer id;
}
