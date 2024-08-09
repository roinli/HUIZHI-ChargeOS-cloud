package com.jingli.admin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yangbin
 * 小区返回前台数据
 */
@Data
@ApiModel("小区数据")
public class PlotResult extends PlotModel {

    @ApiModelProperty("所属地区名称")
    private String regionName;

    @ApiModelProperty("所属省份")
    private String province;

    @ApiModelProperty("所属省份代码")
    private String provinceCode;

    @ApiModelProperty("所属城市")
    private String city;

    @ApiModelProperty("所属城市代码")
    private String cityCode;

    @ApiModelProperty("图片访问地址")
    private String fileUrl;

    @ApiModelProperty("充电桩数量")
    private Integer pileNum;
}
