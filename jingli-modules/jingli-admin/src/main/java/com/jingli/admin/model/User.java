
package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName User
 * @Description: 登录用户 实体
 * @Author yangbin
 * @Version V1.0
 **/

@Getter
@Setter
@TableName("xt_user")
public class User extends UserResult {

    /**
     * 管理员标识 N 否 Y 是
     */
    private String admin;
    /**
     * 微信openId
     */
    @TableField(exist = false)
    private String weixinOpenid;
    /**
     * 岗位ID
     */
    private BigDecimal roleId;
    /**
     * 昵称
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 密码盐
     */
    private String salt;
    /**
     * 部门ID
     */
    private String departid;
    /**
     * 顺序
     */
    private BigDecimal userorder;
    /**
     * 是否为有效用户 Y N
     */
    private String isvalid;
    /**
     * 税务机关代码-x
     */
    private String swryDm;
    /**
     * 操作人员代码-x
     */
    private String czryDm;
    /**
     * 操作人员名称-x
     */
    private String czryMc;


    /**
     * 客户地址
     */
    private String address;

    /**
     * 订购日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date buyDate;

    /**
     * 角色
     */
    @TableField(exist = false)
    private List<RolePO> roles;

    @ApiModelProperty("数据权限 1：全部 2：代理商 本人 3：自定义（分配代理商）")
    private Integer dataPermissions;

    @ApiModelProperty(value = "数据范围", hidden = true)
    @TableField(exist = false)
    private Integer dataScope[];


    /**
     * 平台类型
     */
    private Short deviceType;

    /**
     * 是否分成者
     */
    private Integer isShareholder;

    private String mobile;
}
