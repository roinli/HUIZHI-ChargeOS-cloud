package com.hcp.system.api.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hcp.common.core.annotation.Excel;
import com.hcp.common.core.web.domain.TenantEntity;
import lombok.Builder;
import lombok.Data;

/**
 * 充电桩心跳数据对象 c_heartbeat
 *
 * @author hcp
 * @date 2024-08-06
 */
@Data
@TableName("c_heartbeat")
@Builder
public class Heartbeat extends TenantEntity
{
    private static final long serialVersionUID = 1L;

    /** 充电桩ID */
    @TableId
    @Excel(name = "充电桩ID")
    private String pileId;

    /** 充电桩端口 */
    @Excel(name = "充电桩端口")
    private String deviceId;

    /** 端口状态 */
    @Excel(name = "端口状态")
    private String chargestate;

    /** 源数据 */
    @Excel(name = "源数据")
    private String sourcemsg;




}
