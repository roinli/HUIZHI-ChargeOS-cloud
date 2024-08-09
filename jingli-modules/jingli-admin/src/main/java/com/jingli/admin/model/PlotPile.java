package com.jingli.admin.model;

import lombok.Data;

/**
 * @描述 小区充电桩 地图数据
 * @创建人 yangbin
 */
@Data
public class PlotPile extends Plot {

    /*充电桩数量*/
    private Integer pileCount;

    /*经度*/
    private String lat;

    /*维度*/
    private String lng;

    /*地址*/
    private String address;
}
