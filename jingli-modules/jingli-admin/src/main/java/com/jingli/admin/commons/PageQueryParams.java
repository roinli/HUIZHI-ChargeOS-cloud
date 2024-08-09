package com.jingli.admin.commons;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * @描述 分页查询参数
 * @创建人 Zfenor
 */
@Getter
@Setter
public class PageQueryParams {

    @ApiModelProperty("页数")
    @Min(value = 1, message = "页码参数最小为1")
    private Integer pageNo;

    @ApiModelProperty("每页显示数")
    @Min(value = 1, message = "每页显示条数参数最小为1")
    private Integer pageSize;
}
