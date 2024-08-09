package com.jingli.inter.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

 /**
 * 字典码表;
 * @author : Abdulla
 * @date : 2023-8-25
 */
@ApiModel(value = "字典码表",description = "")
@Data
public class CodeCommon implements Serializable,Cloneable{
    /** 代码 */
    @ApiModelProperty(name = "代码",notes = "")
    private String code ;
    /** 代码名称 */
    @ApiModelProperty(name = "代码名称",notes = "")
    private String codeName ;
    /** 父级代码 */
    @ApiModelProperty(name = "父级代码",notes = "")
    private String parentCode ;
    /** 使用状态 0 使用中 1 禁用 */
    @ApiModelProperty(name = "使用状态 0 使用中 1 禁用",notes = "")
    private Integer state ;
    /** 使用时间 */
    @ApiModelProperty(name = "使用时间",notes = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime ;


}