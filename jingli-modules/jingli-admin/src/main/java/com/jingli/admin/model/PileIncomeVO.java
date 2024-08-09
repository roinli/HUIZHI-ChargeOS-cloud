package com.jingli.admin.model;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class PileIncomeVO implements Serializable {

    private String orderGold;

    private String consumePower;

    private String chargeFee;

    private String serviceFee;

    private String chargeTimes;

    private String realHour;


}
