package com.hcp.operator.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 接口信息对象 hlht_connector_info
 *
 * @author hcp
 * @date 2024-08-11
 */
@Data
@TableName("hlht_connector_info")
public class HlhtConnectorInfo extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 充电设备接口编码 */
    @TableId
    private String connectorId;

    /** 充电设备接口类
型 */
    @Excel(name = "充电设备接口类型")
    private String connectorType;

    /** 额定电压上限 */
    @Excel(name = "额定电压上限")
    private Long voltageUpperLimits;

    /** 额定电压下限 */
    @Excel(name = "额定电压下限")
    private Long voltageLowerLimits;

    /** 额定电流 */
    @Excel(name = "额定电流")
    private Long current;

    /** 额定功率 */
    @Excel(name = "额定功率")
    private BigDecimal power;

    /** 国家标准 */
    @Excel(name = "国家标准")
    private String nationalStandard;

    /** 充电设备接口名
称 */
    @Excel(name = "充电设备接口名 称")
    private String connectorName;

    /** 车位号 */
    @Excel(name = "车位号")
    private String parkNo;

    /** 恒功率电压上限 */
    @Excel(name = "恒功率电压上限")
    private BigDecimal constantVoltageUpperLimits;

    /** 恒功率电压下限 */
    @Excel(name = "恒功率电压下限")
    private BigDecimal constantVoltageLowerLimits;

    /** 恒功率电流上限 */
    @Excel(name = "恒功率电流上限")
    private BigDecimal constantCurrentUpperLimits;

    /** 恒功率电流下限 */
    @Excel(name = "恒功率电流下限")
    private BigDecimal constantCurrentLowerLimits;

    /** 辅助电源 */
    @Excel(name = "辅助电源")
    private Long auxPower;

    /** 运营状态 */
    @Excel(name = "运营状态")
    private String opreateStatus;

    /** 运营时间 */
    @Excel(name = "运营时间")
    private String opreateHours;

    /** 最大充电功率 */
    @Excel(name = "最大充电功率")
    private BigDecimal powerMax;

    /** 设备接口分类 */
    @Excel(name = "设备接口分类")
    private String equipmentClassification;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String equipmentId;



}
