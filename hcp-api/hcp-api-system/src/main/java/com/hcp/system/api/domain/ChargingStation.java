package com.hcp.system.api.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 站点对象 c_charging_station
 *
 * @author hcp
 * @date 2024-08-06
 */
@Data
@TableName("c_charging_station")
public class ChargingStation extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 站点id */
    @TableId
    private Long stationId;

    /** 站点代码 */
    private String stationCode;

    /** 站点名称 */
    @Excel(name = "站点名称")
    private String stationName;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 所属地区代码 */
    @Excel(name = "所属地区代码")
    private String regionCode;

    /** 所属用户 */
    private Long userId;

    /** 经度 */
    @Excel(name = "经度")
    private String lat;

    /** 纬度 */
    @Excel(name = "纬度")
    private String lng;

    /** 停车信息 */
    @Excel(name = "停车信息")
    private String parkCarInfo;

    /** 站点图片 */
    @Excel(name = "站点图片")
    private String fileId;

    /** 平台类型 */
    @Excel(name = "平台类型")
    private Integer deviceType;

    /** 删除标记 */
    private Integer delFlag;

    /** 开票信息 */
    @Excel(name = "开票信息")
    private String receiptStatus;

    /** 配套设施 */
    @Excel(name = "配套设施")
    private String supportingFacilities;

    /** 停车费 */
    @Excel(name = "停车费")
    private String parkCarStatus;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 市/县 */
    @Excel(name = "市/县")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String area;

    /** 是否来自互联互通 */
    @Excel(name = "是否来自互联互通")
    private Long isHlht;

    /** 互联互通编号 */
    @Excel(name = "互联互通编号")
    private String hlhtId;



}
