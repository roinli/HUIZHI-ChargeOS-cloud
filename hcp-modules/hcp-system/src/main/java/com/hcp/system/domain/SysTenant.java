package com.hcp.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.BaseEntity;
import lombok.Data;
/**
 * 租户管理对象 sys_tenant
 *
 * @author vctgo
 * @date 2022-04-11
 */
@Data
@TableName("sys_tenant")
public class SysTenant extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 租户编码 */
    @Excel(name = "租户编码")
    @TableId
    private Long id;

    /** 租户名称 */
    @Excel(name = "租户名称")
    private String tenantName;

    /** 管理员账号 */
    @Excel(name = "管理员账号")
    private String userName;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String userPhone;

    /** 邮箱地址 */
    @Excel(name = "邮箱地址")
    private String userEmail;

    /** 租户套餐*/
    @Excel(name = "租户套餐")
    private Long tenantPackage;

    /** 租赁结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "租赁结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tenantTime;

    /** 租户状态（0正常 1停用） */
    @Excel(name = "租户状态", readConverterExp = "0=正常,1=停用")
    private Integer status;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;



}
