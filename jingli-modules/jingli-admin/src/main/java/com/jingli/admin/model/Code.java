
package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName Code
 * @Description: 公共代码
 * @Author yangbin
 * @Version V1.0
 **/

@Getter
@Setter
@TableName("code_common")
public class Code {

    /**
     * 代码
     */
    @TableId
    @ApiModelProperty("代码")
    private String code;
    /**
     * 代码名称
     */
    @ApiModelProperty("名称")
    private String codeName;
    /**
     * 父级代码
     */
    @ApiModelProperty("父级代码")
    private String parentCode;
    /**
     * 使用状态 0 使用中 1 禁用
     */
    @ApiModelProperty("使用状态：0 使用中 1 禁用")
    private Boolean state;
    /**
     * 使用时间
     */
    @ApiModelProperty(value = "使用时间", hidden = true)
    private Date createTime;
}
