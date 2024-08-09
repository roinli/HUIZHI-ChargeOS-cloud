package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * 参数配置表 sys_config
 */
@Data
@Accessors(chain = true)
@TableName("sys_config")
public class SysConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 参数主键
     */
    @ApiModelProperty(name = "参数主键")
    private Long configId;

    /**
     * 参数名称
     */
    @ApiModelProperty(name = "参数名称")
    private String configName;

    /**
     * 参数键名
     */
    @ApiModelProperty(name = "参数键名")
    private String configKey;

    /**
     * 参数键值
     */
    @ApiModelProperty(name = "参数键值")
    private String configValue;

    /**
     * 系统内置（Y是 N否）
     */
    @ApiModelProperty(name = "系统内置")
    private String configType;

    private String createBy;
    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    @ApiModelProperty("页数")
    @Min(value = 1, message = "页码参数最小为1")
    @TableField(exist = false)
    private Integer pageNo;

    @TableField(exist = false)
    @ApiModelProperty("每页显示数")
    @Min(value = 1, message = "每页显示条数参数最小为1")
    private Integer pageSize;

}
