package com.hcp.system.api.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @className: com.jingli.chargeapi.vo.req.PlotInfoReqVO
 * @author: lv lu
 * @create: 2023-11-13 22:40
 * @description:
 */
@Data
public class PlotInfoReqVO extends BaseReqVO {

    @ApiModelProperty("查询openId")
    private String search_openId;

    @ApiModelProperty("距离")
    private String distance;

    @ApiModelProperty("设备类型 4 四轮车 2 二轮车")
    private Short deviceType;

    private String lat;

    private String lng;

    @ApiModelProperty("排序规则 1 距离 2 价格 3 智能排序(先距离,后价格)")
    private String sortType;

    @ApiModelProperty("城市名称")
    private String city;

    @ApiModelProperty("站点名称")
    private String plotName;

    @ApiModelProperty("站点地址")
    private String address;

    @ApiModelProperty("站点Id")
    private Long stationId;


    @ApiModelProperty("推荐站点 0 空闲较多 1 距离优先")
    private String recommendSitesStatus;

    @ApiModelProperty("充电桩类型 0 慢充 1 快充")
    private List<String> pileType;

    @ApiModelProperty("配套设施 1 免费WIFI 2 便利店 3 洗车 4 厕所")
    private List<String> supportingFacilities;

    @ApiModelProperty("停车费 0 停车收费 1 停车免费")
    private List<String> parkCarStatus;

    @ApiModelProperty("是否可以开发票,0 不可以 1 可以")
    private List<String> receiptStatus;
}
