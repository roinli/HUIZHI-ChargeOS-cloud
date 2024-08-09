package com.jingli.inter.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PlotVO {
    /**
     * 小区id
     */
    private Integer id;

    /**
     * 充电站名称
     */
    private String plotName;

    /**
     * 停车服务信息
     */
    private String parkCarInfo;

    /**
     * 经度
     */
    private float lat;

    /**
     * 维度
     */
    private float lng;

    /**
     * 充电费用
     */
    private float price;

    /**
     * 充电服务费
     */
    private float servicePrice;

    /**
     * 闲数量
     */
    private Integer notBusyNum;

    /**
     * 总数量
     */
    private Integer totalNum;

    /**
     * 慢充数量
     */
    private Integer slowNum;

    /**
     * 快充数量
     */
    private Integer fastNum;

    /**
     * 平台类型 4:四轮车  2:二轮车
     */
    private Short deviceType;

    /**
     * 地址信息
     */
    private String address;

    private String province;
    private String city;
    private String regionName;

    /**
     * 距离
     */
    private double distance;


    @ApiModelProperty("配套设施 1 免费WIFI 2 便利店 3 洗车 4 厕所")
    private List<String> supportingFacilities;

    @ApiModelProperty("停车费 0 停车收费 1 停车免费")
    private List<String> parkCarStatus;

    @ApiModelProperty("是否可以开发票,0 不可以 1 可以")
    private List<String> receiptStatus;

}
