package com.hcp.system.api.domain;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.annotation.Excel.ColumnType;
import com.hcp.common.core.annotation.Excel.Type;
import com.hcp.common.core.annotation.Excels;
import com.hcp.common.core.xss.Xss;
import lombok.NoArgsConstructor;

/**
 * 用户对象 sys_user
 *
 * @author vctgo
 */
@Data
@NoArgsConstructor
@TableName("sys_user")
public class SysUser extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户序号", cellType = ColumnType.NUMERIC, prompt = "用户编号")
    @TableId
    private Long userId;

    /** 部门ID */
    @Excel(name = "部门编号", type = Type.IMPORT)
    private Long deptId;

    /** 用户账号 */
    @Excel(name = "登录名称")
    @Xss(message = "用户账号不能包含脚本字符")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户名称")
    @Xss(message = "用户昵称不能包含脚本字符")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    private String nickName;

    /** 用户类型 */
    @Excel(name = "用户类型")
    @Xss(message = "用户类型不能包含脚本字符")
    @Size(min = 0, max = 2, message = "用户类型长度不能超过2个字符")
    private String userType;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    private String email;

    /** 手机号码 */
    @Excel(name = "手机号码")
    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    private String phonenumber;

    /** 用户性别 */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    private String password;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 最后登录IP */
    @Excel(name = "最后登录IP", type = Type.EXPORT)
    private String loginIp;

    /** 最后登录时间 */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date loginDate;

    @TableField(exist = false)
    /** 部门对象 */
    @Excels({
        @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
        @Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
    })
    private SysDept dept;

    @TableField(exist = false)
    /** 角色对象 */
    private List<SysRole> roles;

    @TableField(exist = false)
    /** 角色组 */
    private Long[] roleIds;

    @TableField(exist = false)
    /** 岗位组 */
    private Long[] postIds;
    @TableField(exist = false)
    /** 角色ID */
    private Long roleId;

    public SysUser(Long userId)
    {
        this.userId = userId;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }
}
