package com.hcp.system.api.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Data;

/**
 * 用户对象 c_member
 *
 * @author hcp
 * @date 2024-08-06
 */
@Data
@TableName("c_member")
public class Member extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户表主键id */
    @TableId
    private Long memberId;

    /** 微信openID */
    @Excel(name = "微信openID")
    private String weixinOpenid;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String headImage;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String userName;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** union_id */
    private String unionId;

    /** app登录openId */
    private String appOpenId;



}
