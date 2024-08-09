package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DESCRIPTION : 角色和菜单关联关系表
 *
 * @author ducf
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("xt_role_menu")
public class RoleMenuPO implements Serializable {

    @TableId
    private Integer id;
    /**
     * 菜单code
     */
    private Integer menuCode;
    /**
     * 角色code
     */
    private Integer roleCode;
    private Date createTime;
    private Date updateTime;


}
