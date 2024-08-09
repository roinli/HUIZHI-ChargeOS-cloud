package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ClassName Plot
 * @Description: 小区信息
 * @Author 杨膑
 * @Version V1.0
 **/
@Getter
@Setter
@ApiModel("小区信息")
public class Plot {
    @ApiModelProperty("小区ID")
    @TableId
    private String id;

    @ApiModelProperty("小区编号")
    private String plotCode;

    @ApiModelProperty("小区名称")
    @NotBlank(message = "小区名称不能为空")
    private String plotName;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("平台类型，2或者4")
    private Short deviceType;

    @ApiModelProperty("代理商id")
    private String userId;
}
