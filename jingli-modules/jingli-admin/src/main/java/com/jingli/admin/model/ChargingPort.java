
package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jingli.admin.util.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName ChargingPort
 * @Description: 充电桩端口
 * @Author yangbin
 * @Version V1.0
 **/

@Getter
@Setter
@ApiModel(value = "端口信息")
public class ChargingPort extends EditPort {

    /**
     * 代理商ID
     */
    @ApiModelProperty(hidden = true)
    private String userId;

    /**
     * 电量
     */
    @ApiModelProperty(value = "电量", hidden = true)
    private String power;
    /**
     * 地址-x
     */
    @ApiModelProperty(value = "地址-x", hidden = true)
    private String address;
    /**
     * mac-x
     */
    @ApiModelProperty(hidden = true)
    private String mac;


    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    /**
     * 总耗电量
     */
    @ApiModelProperty(value = "总耗电量", hidden = true)
    @TableField(exist = false)
    private String totalPowerConsumption;

    @ApiModelProperty(value = "充电桩类型 0:慢充 1:快充")
    @TableField(exist = false)
    private Short pileType;

    private String gunInsert;

    private String orderGold;

    private String realHour;

    public String getTotalPowerConsumption() {
        if (StringUtils.isBlank(totalPowerConsumption)) {
            return StringUtils.formatMoney(Double.valueOf("0"));
        }
        return StringUtils.formatMoney(Double.valueOf(totalPowerConsumption));
    }
}
