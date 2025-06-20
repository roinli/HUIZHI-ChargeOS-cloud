package com.hcp.modules.monitor.task;

import com.hcp.common.message.mail.EmailUtil;
import com.hcp.modules.monitor.domain.MonitorSys;
import com.hcp.modules.monitor.domain.MonitorSysInfo;
import com.hcp.modules.monitor.mapper.MonitorCacheMapper;
import com.hcp.modules.monitor.mapper.MonitorSysInfoMapper;
import com.hcp.modules.monitor.mapper.MonitorSysMapper;
import com.hcp.modules.monitor.utils.LinuxStateForShell;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: vctgo
 * @date: 2022/4/26 15:56
 * @description: 定时获取监控信息
 */
@Component
@Slf4j
public class ScheduledTask {

    /**
     * 线程池
     */
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 40, 2, TimeUnit.MINUTES, new LinkedBlockingDeque<>());

    /**
     * 通知工具类
     */
    @Autowired
    EmailUtil emailUtil;

    @Autowired
    MonitorSysMapper monitorSysMapper;

    @Autowired
    MonitorSysInfoMapper monitorSysInfoMapper;

    @Autowired
    MonitorCacheMapper monitorCacheMapper;

    /**
     *  主机监控信息
     *  启动5s后执行 每隔1分钟监测一次
     */
    @Scheduled(initialDelay = 5000L, fixedRate = 5 * 60 * 1000)
    public void sysInfo()
    {
        //先查询需要监控的信息
        List<MonitorSys> syslist = monitorSysMapper.selectList();
        syslist.stream().forEach(sys ->{
            Map<String, String> result = LinuxStateForShell.runDistanceShell(sys.getUserName(), sys.getUserPassword(), sys.getIp(),sys.getIpPort());
            if (result != null && result.size()>0){
                MonitorSysInfo monitorSysInfo = LinuxStateForShell.disposeResultMessage(result);
                if (monitorSysInfo!=null){
                    monitorSysInfo.setIpId(sys.getId());
                    monitorSysInfoMapper.insert(monitorSysInfo);
                    if (sys.getIsOnline()==1){
                        //判断查询出的状态 如果是掉线就改成在线
                        sys.setIsOnline(0);
                        monitorSysMapper.updateById(sys);
                    }
                }
            }else {
                //修改在线状态
                sys.setIsOnline(1);
                monitorSysMapper.updateById(sys);
            }
        });
    }

    /**
     *  发送报警信息
     *  启动5分钟后执行 每隔5分钟执行一次
     *  读取所有配置表 监控当前状态 和报警配置
     */
    @Scheduled(initialDelay = 1 * 60 * 1000, fixedRate = 5 *60 * 1000)
    public void sendMessage(){
        //机器监控
        List<MonitorSys> syslist = monitorSysMapper.selectList("is_online",1);
        syslist.stream().forEach(sys ->{
            if (sys.getMessageType() == 0){
                //短信业务
                Runnable runnable = () -> {
                    //短信工具类
                };
                executor.execute(runnable);
            }else {
                //邮件业务
                Runnable runnable = () -> {
                    //邮件工具类
                    emailUtil.sendSimpleMail("缓存服务器掉线提醒","您的地址为"+sys.getIp()+"的服务器已经掉线,请尽快恢复!",sys.getUserEmail());
                };
                executor.execute(runnable);
            }
            //机器判断 内存 硬盘  cpu是否有超过限额的

        });
    }
}
