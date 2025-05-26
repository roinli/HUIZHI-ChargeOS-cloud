package com.hcp.system.api.domain.vo;

import lombok.Data;

@Data
public class PilePortVO {
    private Integer id;

    private String state;

    private String pileId;

    private Integer pileType;

    private Short deviceType;

    private float price;

    private float servicePrice;

    private float hours;
    private int isHlht;
    private String hlhdId;

}
