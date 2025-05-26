package com.hcp.system.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 小程序对象 sys_miniapp
 *
 * @author hcp
 * @date 2024-08-10
 */
@Data
@TableName("sys_miniapp")
public class Miniapp extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 小程序appid */
    @Excel(name = "小程序appid")
    @TableId(type = IdType.INPUT)
    private String appId;

    /** 小程序名称 */
    @Excel(name = "小程序名称")
    private String name;

    /** 小程序图标 */
    @Excel(name = "小程序图标")
    private String logo;

    /** 小程序secret */
    @Excel(name = "小程序secret")
    private String appSecret;

    /** 商户号 */
    @Excel(name = "商户号")
    private String mchId;

    /** 商户密钥 */
    @Excel(name = "商户密钥")
    private String mchKey;

    /** 证书路径 */
    @Excel(name = "证书路径")
    private String keyPath;

    /** 状态0正常1禁用 */
    private String status;

    /** 删除状态0正常1删除 */
    private String delFlag;



}
