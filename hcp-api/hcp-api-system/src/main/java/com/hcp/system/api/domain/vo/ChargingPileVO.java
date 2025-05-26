package com.hcp.system.api.domain.vo;

import com.hcp.system.api.domain.ChargingPile;
import com.hcp.system.api.domain.ChargingPort;
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
    private String stationName;

    /**
     * 充电桩端口List
     */
    private List<ChargingPort> list;

}
