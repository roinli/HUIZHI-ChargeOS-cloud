package com.hcp.operator.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 站点信息对象 hlht_station_info
 *
 * @author hcp
 * @date 2024-08-11
 */
@Data
@TableName("hlht_station_info")
public class HlhtStationInfo extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId
    private String stationId;

    /** $column.columnComment */
    @Excel(name = "站点名称", readConverterExp = "$column.readConverterExp()")
    private String stationName;

    /** 行政区划代码，区县地区码
（6 位）+运营商 ID（9 位）
+充换电站 ID。 */
    @Excel(name = "行政区划代码，区县地区码 ", readConverterExp = "6=,位=")
    private String stationUniqueNumber;

    /** 基础设施运营商，场站属
主的组织机构代码九位 */
    @Excel(name = "基础设施运营商")
    private String equipmentOwnerId;

    /** 运营商ID */
    @Excel(name = "运营商ID")
    private String operatorId;

    /** 国家代码 */
    @Excel(name = "国家代码")
    private String countryCode;

    /** 省市辖区的区县代码 */
    @Excel(name = "省市辖区的区县代码")
    private String areaCode;

    /** 填写内容为12位行政区划代
码，1- 6位为县及以上行政
区划代码，7-12 位
为县以下区划代码；参考地
址 */
    @Excel(name = "行政区划代码")
    private String areaCodeCountryside;

    /** 充电站详细地址 */
    @Excel(name = "充电站详细地址")
    private String address;

    /** 站点类型：
1：公共
50：个人
100：公交（专用）
101：环卫（专用）
102：物流（专用）
103： 出租车（专用）
104：分时租赁（专用）
105：小区共享（专用）
106：单位（专用）
107：私人共享设备（专
用）
255：其它 */
    @Excel(name = "站点类型：1：公共50：个人100：公交", readConverterExp = "专=用")
    private String stationType;

    /** 0：未知
1：建设中
5：关闭下线
6：维护中
50：正常使用
51：歇业 */
    @Excel(name = "0：未知1：建设中5：关闭下线6：维护中50：正常使用51：歇业")
    private String stationStatus;

    /** 站点经度 */
    @Excel(name = "站点经度")
    private BigDecimal stationLng;

    /** 站点维度 */
    @Excel(name = "站点维度")
    private BigDecimal stationLat;

    /** 站点电话，能够联系场站工
作人员进行协 助的电话 */
    @Excel(name = "站点电话，能够联系场站工作人员进行协 助的电话")
    private String stationTel;

    /** ⻋位数量，可停放充电的⻋
位总数
默认为0，表示未知 */
    @Excel(name = "⻋位数量")
    private Long parkNum;

    /** 服务电话 */
    @Excel(name = "服务电话")
    private String serviceTel;

    /** 站点指引，描述性文字，用
于引导车主找 到充电车 */
    @Excel(name = "站点指引")
    private String siteGuide;

    /** 1：充电站
2：换电站
3：充换电一体站 */
    @Excel(name = "1：充电站2：换电站3：充换电一体站")
    private Long stationClassification;

    /** 1：通用（可为 3 种及 3 种
以上的换电车型进行换电的
换电站）
2：非通用
注：站点分类为 2 或 3 时，
此字段为必填项；站点分类 */
    @Excel(name = "1：通用", readConverterExp = "")
    private Long generalApplicationType;

    /** 服务车型描
述 */
    @Excel(name = "服务车型描述")
    private String matchCars;

    /** 0：免费
1：不免费
2：限时免费停车
3：充电限时减免
255：参考场地实际收费标
准 */
    @Excel(name = "0：免费1：不免费2：限时免费停车3：充电限时减免255：参考场地实际收费标准")
    private Long parkType;

    /** 点照片：充电设备照片，
充电车位照片，停车场入口
照片 */
    @Excel(name = "设备照片")
    private String pictures;

    /** 车位楼层及数量信息 */
    @Excel(name = "车位楼层及数量信息")
    private String parkInfo;

    /** 营业时间描述 */
    @Excel(name = "营业时间描述")
    private String busineHours;

    /** 充电电费描述，24小时
内可多段，每段大于等
于15分钟，标准格式： */
    @Excel(name = "充电电费描述")
    private String electricityFee;

    /** 固定格式文本 */
    @Excel(name = "固定格式文本")
    private String swapFee;

    /** 服务费 */
    @Excel(name = "服务费")
    private String serviceFee;

    /** 停车描述，24小时内可多段
。rules : 车费规则描述 */
    @Excel(name = "停车描述")
    private String parkFee;

    /** 7*24小时
营业 */
    @Excel(name = "7*24小时营业")
    private Long roundTheClock;

    /** 支付方式：刷卡、线上、
现金。
电子钱包类卡位刷卡，
身份鉴权卡、微信/支付
宝、APP为线上 */
    @Excel(name = "支付方式")
    private String payment;

    /** 是否支持预约：
0：不支持
1：支持
默认为0 */
    @Excel(name = "是否支持预约0：不支持1：支持 默认为0")
    private Long supportOrder;

    /** 仓位数量 */
    @Excel(name = "仓位数量")
    private Long positionNum;

    /** 整站配电容
量 */
    @Excel(name = "整站配电容量")
    private Long ratedCapacity;

    /** 通道类型 */
    @Excel(name = "通道类型")
    private Long channelType;

    /** 电费类型 */
    @Excel(name = "电费类型")
    private Long electricityType;

    /** 报装类型 */
    @Excel(name = "报装类型")
    private Long businessExpandType;

    /** 报装电源容
量 */
    @Excel(name = "报装电源容量")
    private BigDecimal capacity;

    /** 站点额定总
功率 */
    @Excel(name = "站点额定总功率")
    private BigDecimal ratedPower;

    /** 峰谷分时 */
    @Excel(name = "峰谷分时")
    private Long periodFee;

    /** 正式投运时
间 */
    @Excel(name = "正式投运时间")
    private String officialRunTime;

    /** 充换电站方
位 */
    @Excel(name = "充换电站方位")
    private Long stationOrientation;

    /** 充电场站建设用地面积 */
    @Excel(name = "充电场站建设用地面积")
    private BigDecimal stationArea;

    /** 充换电站人
工值守 */
    @Excel(name = "充换电站人工值守")
    private Long havePerson;

    /** 视频监控配
套情况 */
    @Excel(name = "视频监控配套情况")
    private Long videoMonitor;

    /** 周边配套设
施 */
    @Excel(name = "周边配套设施")
    private String supportingFacilities;

    /** 是否有小票
机 */
    @Excel(name = "是否有小票机")
    private Long printerFlag;

    /** 是否有道闸 */
    @Excel(name = "是否有道闸")
    private Long barrierFlag;

    /** 是否有地锁 */
    @Excel(name = "是否有地锁")
    private Long parkingLockFlag;

    /** 换电计费类
型 */
    @Excel(name = "换电计费类型")
    private String changeType;

    /** 换电计算公
式 */
    @Excel(name = "换电计算公式")
    private String expression;

    /** 充电设备列
表 */
    @Excel(name = "充电设备列表")
    private String equipmentInfos;

    /** 建站时间 */
    @Excel(name = "建站时间")
    private String buildTime;

    /** 最后后更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后后更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdateTime;

    /** 建设场所 */
    @Excel(name = "建设场所")
    private Long construction;

    /** 场所ID */
    @Excel(name = "场所ID")
    private Long plotId;



}
