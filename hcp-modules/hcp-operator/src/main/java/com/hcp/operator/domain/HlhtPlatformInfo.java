package com.hcp.operator.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 平台信息对象 hlht_platform_info
 *
 * @author hcp
 * @date 2024-08-11
 */
@Data
@TableName("hlht_platform_info")
public class HlhtPlatformInfo extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId
    private Long id;

    /** 对接编码 */
    @Excel(name = "对接编码")
    private String platformId;

    /** 对接平台名称 */
    @Excel(name = "对接平台名称")
    private String platformName;

    /** 对接人姓名 */
    @Excel(name = "对接人姓名")
    private String contactPerson;

    /** 对接人联系方式 */
    @Excel(name = "对接人联系方式")
    private String mobile;

    /** 对接方秘钥 */
    @Excel(name = "对接方秘钥")
    private String platformSecretOrigin;

    /** 对接方秘钥(加密) */
    @Excel(name = "对接方秘钥(加密)")
    private String platformSecret;

    /** 签名秘钥 */
    @Excel(name = "签名秘钥")
    private String signSecret;

    /** 数据加密秘钥 */
    @Excel(name = "数据加密秘钥")
    private String dataSecret;

    /** 初始化向量 */
    @Excel(name = "初始化向量")
    private String dataSecretIv;

    /** 互联互通地址 */
    @Excel(name = "互联互通地址")
    private String apiUrl;

    /** 服务器IP */
    @Excel(name = "服务器IP")
    private String accessIp;

    /** 对接环境 */
    @Excel(name = "对接环境")
    private String env;

    /** 我方平台ID */
    @Excel(name = "我方平台ID")
    private String jingliPlatformId;

    /** 我放平台key */
    @Excel(name = "我放平台key")
    private String jingliPlatformSecret;

    /** 我方签名密钥 */
    @Excel(name = "我方签名密钥")
    private String jingliSigSecret;

    /** 我方数据加密密钥 */
    @Excel(name = "我方数据加密密钥")
    private String jingliDataSecret;

    /** 我方初始化向量 */
    @Excel(name = "我方初始化向量")
    private String jingliDataSecretIv;

    /** 是否推送1推送0不推送 */
    @Excel(name = "是否推送1推送0不推送")
    private String pushEnable;

    @TableField
    private String startTime;

    @TableField
    private String endTime;

}
