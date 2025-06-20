package com.hcp.common.mybatisplus.common;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 多租户配置
 *
 * @author dhr
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "hcp.tenant")
@Component
public class MyTenantProperties {
    /**
     * 多租户字段名称
     */
    private String column = "tenant_id";

    /**
     * 多租户需要过滤的数据表(包含系统表)
     */
    private List<String> ignoreTables = new ArrayList<>();

}
