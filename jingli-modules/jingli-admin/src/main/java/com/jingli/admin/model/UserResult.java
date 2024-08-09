package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @描述 运营人员信息返回数据
 * @创建人 yangbin
 */
@Data
@ApiModel("UserResult：运营人员数据")
public class UserResult extends EditUser {


    @ApiModelProperty(value = "注册时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @ApiModelProperty("所属角色")
    @TableField(exist = false)
    private String rolesArr;

    @ApiModelProperty("数据权限")
    @TableField(exist = false)
    private String dataScopeArr;

}
