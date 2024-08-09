package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @描述 端口修改
 * @创建人 yangbin
 */
@Data
public class EditPort extends AddPort {


    @TableId
    @ApiModelProperty("端口ID，修改时必填")
    private Integer id;

}
