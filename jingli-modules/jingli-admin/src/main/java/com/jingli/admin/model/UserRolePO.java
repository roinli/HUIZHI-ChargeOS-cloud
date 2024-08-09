package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @描述 用户角色关联表
 * @创建人 yangbin
 */
@Data
@NoArgsConstructor
@TableName("xt_user_role")
public class UserRolePO {


    @TableId
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 角色code
     */
    private Integer roleCode;
    private Date createTime;
    private Date updateTime;

    public UserRolePO(Integer userId, Integer roleCode) {
        this.userId = userId;
        this.roleCode = roleCode;
    }
}
