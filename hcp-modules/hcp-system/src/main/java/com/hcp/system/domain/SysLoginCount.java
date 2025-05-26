package com.hcp.system.domain;

import lombok.Data;

/**
 * 累积网站信息统计
 *
 * @author vctgo
 */
@Data
public class SysLoginCount
{
    /** 在线人数 */
    private String onlineCount;

    /** 注册人数 */
    private String registerCount;

    /** 租户入驻数 */
    private String tenantCount;

    /** 累积访问次数 */
    private String loginCount;

}
