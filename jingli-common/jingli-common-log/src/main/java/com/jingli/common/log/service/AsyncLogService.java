package com.jingli.common.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.jingli.common.core.constant.SecurityConstants;
import com.jingli.system.api.RemoteLogService;
import com.jingli.system.api.domain.SysOperLog;

/**
 * 异步调用日志服务
 * 
 * @author jingli
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
    public void saveSysLog(SysOperLog sysOperLog) throws Exception
    {
        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
    }
}
