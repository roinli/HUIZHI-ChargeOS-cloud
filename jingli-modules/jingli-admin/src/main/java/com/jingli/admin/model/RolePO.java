
package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 角色
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("xt_role")
public class RolePO implements Serializable {

    @TableId
    private Integer id;
    /**
     * 角色唯一标识
     */
    private Integer roleCode;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 描述
     */
    private String description;
    /**
     * 角色类型 0=运营端角色  1=代理商端角色
     */
    private Integer roleType;
    /**
     * 代理商角色的数据权限
     */
    private String dataScope;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date updateTime;

    // 非数据库字段
    @TableField(exist = false)
    private boolean isCheck = false;

}
