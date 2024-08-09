package com.jingli.admin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @描述 代理商select框数据
 * @创建人 yangbin
 */
@ApiModel("代理商select 数据")
@Data
public class OperatorSelect {

    @ApiModelProperty("代理商id")
    private Integer operatorId;

    @ApiModelProperty("代理商名称")
    private String operatorNm;

}
