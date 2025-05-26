package com.hcp.mp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName c_credit_detail
 */
@TableName(value ="c_credit_detail")
@Data
public class CreditDetail implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long memberId;

    /**
     * 
     */
    private Integer type;

    /**
     * 
     */
    private String openId;

    /**
     * 
     */
    private Integer number;

    /**
     * 
     */
    private String orderNo;

    /**
     * 
     */
    private Date createTime;


}