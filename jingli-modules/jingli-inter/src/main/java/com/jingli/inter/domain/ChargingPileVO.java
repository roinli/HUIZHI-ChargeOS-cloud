package com.jingli.inter.domain;

import com.jingli.inter.entity.ChargingPile;
import com.jingli.inter.entity.ChargingPort;
import lombok.Data;

import java.util.List;
@Data
public class ChargingPileVO extends ChargingPile {
    private String province;
    private String city;
    private String regionName;

    /**
     * 充电站名称
     */
    private String plotName;

    /**
     * 充电桩端口List
     */
    private List<ChargingPort> list;

}
