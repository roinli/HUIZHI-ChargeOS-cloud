package com.jingli.inter.domain;

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

}
