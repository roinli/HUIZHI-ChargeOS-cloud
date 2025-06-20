package com.hcp.operator.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryChargePileVo  {

    private int pageNum;
    private int pageSize;

    @ApiModelProperty(value = "用户")
    private Long userId;

    @ApiModelProperty(value = "设备类型")
    private String deviceType;

    @ApiModelProperty(value = "充电桩编号")
    private String chargeNo;

    @ApiModelProperty(value = "充电桩类型/快充慢充")
    private String pileType;

    @ApiModelProperty(value = "充电桩状态/启动禁用")
    private String pileStatus;

    @ApiModelProperty(value = "运行状态")
    private String runningStatus;

    @ApiModelProperty(value = "端口状态")
    private String portStatus;

    private String plotId;

    private String plotName;



    @ApiModelProperty(value = "是否急停")
    private String stopStatus;

    private String startTime;

    private String endTime;

    private String keyWord;




}
