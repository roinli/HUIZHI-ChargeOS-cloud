package com.hcp.common.core.web.domain;

import lombok.Data;


/**
 * Entity基类
 *
 * @author vctgo
 */
@Data
public class TenantEntity extends BaseEntity
{

    /** 租户ID */
    private Long tenantId;

}
