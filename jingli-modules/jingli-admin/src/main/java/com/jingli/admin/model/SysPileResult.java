package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jingli.admin.util.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @描述 充电桩运营端列表数据
 * @创建人 yangbin
 */
@Data
public class SysPileResult extends PileResult {

    @ApiModelProperty(value = "充电桩收费类型 2001：收费 2002：免费")
    private String cpFeeType;

    @ApiModelProperty("SIM卡序列号")
    private String simCard;

    @ApiModelProperty("SIM卡到期时间")
    private String simDueTime;

    @ApiModelProperty("设备温度")
    private String equipTemperature;

    @ApiModelProperty("设备cpu温度")
    private String equipCpuTemperature;

    @ApiModelProperty(value = "端口数")
    @TableField(exist = false)
    private Integer portCount;

    @ApiModelProperty("代理商昵称")
    private String userName;

    @ApiModelProperty("代理商Id")
    private Integer userId;

    @ApiModelProperty("代理商真实姓名")
    private String realName;

    @ApiModelProperty("所属地区名称")
    private String regionName;

    @ApiModelProperty("所属地区代码")
    private String regionCode;

    @ApiModelProperty("所属省份")
    private String province;

    @ApiModelProperty("所属省份代码")
    private String provinceCode;

    @ApiModelProperty("所属城市")
    private String city;

    @ApiModelProperty("所属城市代码")
    private String cityCode;

    @ApiModelProperty("电流信息")
    private String electricity;

    @ApiModelProperty("电压信息")
    private String voltage;

    @ApiModelProperty("最大功率")
    private String maxPower;

    private PileIncomeVO pileIncomeVO;

    /**
     * 总耗电量
     */
    @ApiModelProperty(value = "总耗电量", hidden = true)
    @TableField(exist = false)
    private String totalPowerConsumption;

    public String getTotalPowerConsumption() {
        if (StringUtils.isBlank(totalPowerConsumption)) {
            return StringUtils.formatMoney(Double.valueOf("0"));
        }
        return StringUtils.formatMoney(Double.valueOf(totalPowerConsumption));
    }

}
