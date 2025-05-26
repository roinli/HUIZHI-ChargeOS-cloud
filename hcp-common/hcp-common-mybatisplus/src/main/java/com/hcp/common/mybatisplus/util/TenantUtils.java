package com.hcp.common.mybatisplus.util;

import com.hcp.common.core.context.SecurityContextHolder;

/**
 * 多租户 Util
 *
 * @author 芋道源码
 */
public class TenantUtils {

    /**
     * 使用指定租户，执行对应的逻辑
     *
     * 注意，如果当前是忽略租户的情况下，会被强制设置成不忽略租户
     * 当然，执行完成后，还是会恢复回去
     *
     * @param tenantId 租户编号
     * @param runnable 逻辑
     */
    public static void execute(Long tenantId, Runnable runnable) {
        Long oldTenantId = SecurityContextHolder.getTenantId();
        try {
            SecurityContextHolder.setTenantId(String.valueOf(tenantId));
            // 执行逻辑
            runnable.run();
        } finally {
            SecurityContextHolder.setTenantId(String.valueOf(oldTenantId));
        }
    }

}
