package com.hcp.system.api.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlotPileVo implements Serializable {
    private String pileNo;

    private String electricity;

    private String voltage;

    private int maxPower;

    /**
     * 充电桩类型 0:慢充 1:快充
     */
    private Short pileType;

    /**
     * 总数
     */
    private int totalNum;

    /**
     * 闲数量
     */
    private int notBusyNum;

    private float price;

    private float servicePrice;

    private float hours;
    private int isHlht;
    private String hlhdId;


}
