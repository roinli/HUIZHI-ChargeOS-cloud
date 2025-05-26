package com.hcp.operator.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 设备列表对象 hlht_equipment_info
 *
 * @author hcp
 * @date 2024-08-11
 */
@Data
@TableName("hlht_equipment_info")
public class HlhtEquipmentInfo extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 充电设备唯一标识，运营商自定义，运营商内唯一 */
    @TableId
    private String equipmentId;

    /** 是否支持即插即充 */
    @Excel(name = "是否支持即插即充")
    private String svin;

    /** 是否支持自动调节功率 */
    @Excel(name = "是否支持自动调节功率")
    private String sautoPower;

    /** 设备分类 */
    @Excel(name = "设备分类")
    private String equipmentClassification;

    /** 设备唯一编码 */
    @Excel(name = "设备唯一编码")
    private String equipmentUniqueNumber;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String equipmentType;

    /** 充电设备总功率 */
    @Excel(name = "充电设备总功率")
    private BigDecimal power;

    /** 设备生产商组织机构代码 */
    @Excel(name = "设备生产商组织机构代码")
    private String manufacturerId;

    /** 设备生产商名称 */
    @Excel(name = "设备生产商名称")
    private String manufacturerName;

    /** 设备型号 */
    @Excel(name = "设备型号")
    private String equipmentModel;

    /** 设备生产日期 */
    @Excel(name = "设备生产日期")
    private String productionDate;

    /** 充电设备经度 */
    @Excel(name = "充电设备经度")
    private BigDecimal equipmentLng;

    /** 充电设备纬度 */
    @Excel(name = "充电设备纬度")
    private BigDecimal equipmentLat;

    /** 充电设备名称 */
    @Excel(name = "充电设备名称")
    private String equipmentName;

    /** 额定电压上限 */
    @Excel(name = "额定电压上限")
    private Long voltageUpperLimits;

    /** 额定电压下限 */
    @Excel(name = "额定电压下限")
    private Long voltageLowerLimits;

    /** 额定电流 */
    @Excel(name = "额定电流")
    private Long current;

    /** 充电站 */
    @Excel(name = "充电站")
    private String stationId;



}
