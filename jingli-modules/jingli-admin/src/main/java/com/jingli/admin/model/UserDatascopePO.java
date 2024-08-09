package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DESCRIPTION : 运营者代理商权限关联关系
 *
 * @author ducf
 */
@Data
@TableName("c_user_datascope")
@NoArgsConstructor
public class UserDatascopePO {

    @TableId
    private Integer id;

    /**
     * 运营者id
     */
    private Integer userId;

    /**
     * 代理商id
     */
    private Integer operatorId;

    public UserDatascopePO(Integer userId, Integer operatorId) {
        this.userId = userId;
        this.operatorId = operatorId;
    }
}
