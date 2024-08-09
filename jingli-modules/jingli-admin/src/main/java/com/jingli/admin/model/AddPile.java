package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @描述 充电桩添加数据
 * @创建人 yangbin
 */
@Data
public class AddPile {

    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "充电桩编号")
    @NotBlank(message = "不能为空")
    private String id;

    @ApiModelProperty(value = "充电桩名称")
    private String pkname;

    @ApiModelProperty(value = "所属小区ID")
    private Integer plot;

    @ApiModelProperty(value = "所属区域id")
    @TableField(exist = false)
    private Integer regionCode;

    @ApiModelProperty(value = "小区名称")
    @TableField(exist = false)
    private String plotName;

    @ApiModelProperty(value = "端口数")
    @TableField(exist = false)
    private Integer portCount;

    @ApiModelProperty("设备类型 2:二轮车 4:四轮车")
    private short deviceType = 4;

    @ApiModelProperty("设备状态  1:启用  0:禁用")
    private short pileStatus;

    @ApiModelProperty("电流信息")
    private String electricity;

    @ApiModelProperty("电压信息")
    private String voltage;

    @ApiModelProperty("最大功率")
    private Integer maxPower;

    @ApiModelProperty("充电桩类型 0:慢充 1:快充")
    private Short pileType;

    /**
     * 费用需要更新，1:需要更新 0:不需要
     */
    private Integer feeNeedUpdate;
}
