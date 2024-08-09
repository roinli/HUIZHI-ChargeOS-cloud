package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author yangbin
 * @描述 小区实体类
 */
@Data
@TableName("c_plot")
@ToString
public class PlotModel extends Plot {

    @ApiModelProperty("地址")
    @NotBlank(message = "地址不能为空")
    private String Address;

    @ApiModelProperty("所属地区代码")
    @NotNull(message = "所属地区不能为空")
    private Integer regionCode;

    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("经度")
    @NotBlank(message = "经度不能为空")
    private String lat;

    @ApiModelProperty("纬度")
    @NotBlank(message = "纬度不能为空")
    private String lng;

    @ApiModelProperty("停车收费说明")
    private String parkCarInfo;

    @ApiModelProperty("门头照片文件id")
    private String fileId;


    @TableField(exist = false)
    @ApiModelProperty("配套设施 1 免费WIFI 2 便利店 3 洗车 4 厕所")
    private List<String> supportingFacilitiesList;

    @TableField(exist = false)
    @ApiModelProperty("停车费 0 停车收费 1 停车免费")
    private List<String> parkCarStatusList;

    @TableField(exist = false)
    @ApiModelProperty("是否可以开发票,0 不可以 1 可以")
    private List<String> receiptStatusList;

    @ApiModelProperty("配套设施 1 免费WIFI 2 便利店 3 洗车 4 厕所")
    private String supportingFacilities;

    @ApiModelProperty("停车费 0 停车收费 1 停车免费")
    private String parkCarStatus;

    @ApiModelProperty("是否可以开发票,0 不可以 1 可以")
    private String receiptStatus;
}
