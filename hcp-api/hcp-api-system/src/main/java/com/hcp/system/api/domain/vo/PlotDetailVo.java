package com.hcp.system.api.domain.vo;

import lombok.Data;

import java.util.List;
@Data
public class PlotDetailVo {
    private String province;
    private String city;
    private String regionName;

    private String address;

    private String plotName;
    private String stationId;

    private String stationName;

    private String parkCarInfo;

    private Integer maxPower;


    private float startTime;

    private float endTime;

    private Float price;

    private Float servicePrice;

    private Float hours;

    private String url;

    /**
     * 经度
     */
    private float lat;

    /**
     * 维度
     */
    private float lng;

    /**
     * 快充总数
     */
    private Integer fastTotalNum = 0;

    /**
     * 快充闲数量
     */
    private Integer fastNotBusyNum = 0;

    /**
     * 慢充总数
     */
    private Integer slowTotalNum = 0;

    /**
     * 慢充闲数量
     */
    private Integer slowNotBusyNum = 0;

    /**
     * 慢充桩
     */
    private List<PlotPileVo> slowPileList;

    /**
     * 快充桩
     */
    private List<PlotPileVo> fastPileList;

    private int isHlht;
    private String hlhtId;

}
