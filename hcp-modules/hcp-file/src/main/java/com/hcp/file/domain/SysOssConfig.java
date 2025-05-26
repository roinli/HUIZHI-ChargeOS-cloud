package com.hcp.file.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 对象存储对象 sys_oss_config
 *
 * @author hcp
 * @date 2024-08-07
 */
@Data
@TableName("sys_oss_config")
public class SysOssConfig extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /** 存储平台标识 */
    @Excel(name = "存储平台标识")
    private String platformId;

    /** 令牌 */
    @Excel(name = "令牌")
    private String accessKey;

    /** 密钥 */
    @Excel(name = "密钥")
    private String secretKey;

    /** 存储地址 */
    @Excel(name = "存储地址")
    private String endPoint;

    /** 存储同名称 */
    @Excel(name = "存储同名称")
    private String bucketName;

    /** 访问地址 */
    @Excel(name = "访问地址")
    private String domain;

    /** 路径 */
    @Excel(name = "路径")
    private String basePath;

    /** 是否默认0是1不是 */
    @Excel(name = "是否默认0是1不是")
    private String isDefault;

    /** 状态0正常1禁用 */
    @Excel(name = "状态0正常1禁用")
    private String status;

    /** 删除标志 */
    private String delFlag;



}
