package com.jingli.inter.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 充电桩端口表;
 *
 * @author : Abdulla
 * @date : 2023-8-25
 */
@ApiModel(value = "充电桩端口表", description = "")
@Data
public class ChargingPort implements Serializable, Cloneable {
    /**
     *
     */
    @ApiModelProperty(name = "chargingPortId", notes = "")
    private Integer id;
    /**
     * 用户ID
     */
    @ApiModelProperty(name = "用户ID", notes = "")
    private String userId;
    /**
     * 插口状态 N是未使用 Y 在使用 F是故障
     */
    @ApiModelProperty(name = "插口状态 N是未使用 Y 在使用 F是故障", notes = "")
    private String state;
    /**
     * 电量
     */
    @ApiModelProperty(name = "电量", notes = "")
    private String power;
    /**
     * 经度-x
     */
    @ApiModelProperty(name = "经度-x", notes = "")
    private String baiduX;
    /**
     * 维度-x
     */
    @ApiModelProperty(name = "维度-x", notes = "")
    private String baiduY;
    /**
     * 地址-x
     */
    @ApiModelProperty(name = "地址-x", notes = "")
    private String address;
    /**
     * mac-x
     */
    @ApiModelProperty(name = "mac-x", notes = "")
    private String mac;
    /**
     * 端口名字编号
     */
    @ApiModelProperty(name = "端口名字编号", notes = "")
    private String name;
    /**
     * 充电桩ID
     */
    @ApiModelProperty(name = "充电桩ID", notes = "")
    private Integer parkid;
    /**
     * 端口ID，用来和硬件交互也就是netty
     */
    @ApiModelProperty(name = "端口ID，用来和硬件交互也就是netty", notes = "")
    private String deviceid;

    @ApiModelProperty(name = "创建时间", notes = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    /**
     * 电桩名称
     */
    private String pileName;

    private String gunInsert;

    /**
     * 充电桩端口 对应deviceId字段
     */
    private String devicePortNum;


}