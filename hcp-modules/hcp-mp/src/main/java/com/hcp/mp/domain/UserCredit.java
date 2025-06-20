package com.hcp.mp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName c_user_credit
 */
@TableName(value ="c_user_credit")
@Data
public class UserCredit implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long memberId;

    /**
     * 
     */
    private String openId;

    /**
     * 
     */
    private Integer credit;


}