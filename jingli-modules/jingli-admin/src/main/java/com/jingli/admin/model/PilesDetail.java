package com.jingli.admin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DESCRIPTION : 设备统计
 *
 * @author ducf
 */
@ApiModel(value = "设备统计信息")
@Data
public class PilesDetail {

    @ApiModelProperty(value = "充电桩编号")
    private String id;

    @ApiModelProperty(value = "充电桩名称")
    private String name;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "耗电量")
    private BigDecimal power;

    @ApiModelProperty(value = "使用次数")
    private BigDecimal usedNumber;

    @ApiModelProperty(value = "有效端口数量")
    private Integer goodPort;

    @ApiModelProperty(value = "损坏端口数量")
    private Integer badPort;

    @ApiModelProperty(value = "运行状态 0:运行,1离线")
    private String runningStates;

    private String runningStatesName;

    public void setRunningStates(String runningStates) {
        this.runningStates = runningStates;
        if (runningStates == null) {
            this.runningStatesName = "";
        } else if ("0".equals(runningStates)) {
            this.runningStatesName = "运行";
        } else if ("1".equals(runningStates)) {
            this.runningStatesName = "离线";
        }
    }
}
