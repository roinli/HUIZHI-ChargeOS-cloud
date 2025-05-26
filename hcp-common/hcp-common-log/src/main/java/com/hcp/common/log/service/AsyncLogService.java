package com.hcp.common.log.service;

import com.hcp.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.hcp.common.core.constant.SecurityConstants;
import com.hcp.system.api.RemoteLogService;
import com.hcp.system.api.domain.SysOperLog;

/**
 * 异步调用日志服务
 *
 * @author vctgo
 */
@Service
public class AsyncLogService
{
    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLog sysOperLog) throws Exception {
        sysOperLog.setTenantId(SecurityUtils.getTenantId());
        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
    }
}
