package com.jingli.inter.domain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
  * 功能描述：用户订单信息
  */
@Data
public class UserOrderVO {
	private String name;
	private String orderstate;
	private String ordernumber;
	private String platenumber;
	private String actualendtime;
	private String actualstarttime;
	private String imgurl;
	private String devideId;
	private String person;
	private String id;
	private String rentermobile;
	private String price;
	private String ordergold;
	private String createTime;

	/**
	 * 实际充电时长
	 */
	private String realHour;

	/**
	 * 充电桩id
	 */
	private String parkId;

	/**
	 * 充电开始时间
	 */
	private String startTime;
	/**
	 * 充电结束时间
	 */
	private String endTime;
	/**
	 * 充电耗电量 kw
	 */
	private String consumePower;
	/**
	 * 计划充电时长
	 */
	private String hour;

	/**
	 * 卡号
	 */
	private String cardNo;
	/**
	 * 代理商手机号
	 */
	private String mobile;

	/**
	 * 订单类型 4||2
	 */
	private Short deviceType;

	/**
	 * 充电状态： 充电状态 9001 未开始 9002 充电中 9003 已完成
	 */
	private String chargeStatus;

	/**
	 * 订单类型，8001 按小时计费（取决于用户选择,默认） 8002 按度数计费(智能充满)
	 */
	private String orderType;

	/**
	 * 订单来源，微信小程序：1，H5：2  卡：3
	 */
	private Integer orderSource;

	@ApiModelProperty("站点名称")
	private String plotName;

}
