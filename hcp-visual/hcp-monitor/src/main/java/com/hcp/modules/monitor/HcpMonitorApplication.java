package com.hcp.modules.monitor;

import com.hcp.common.security.annotation.EnableCustomConfig;
import com.hcp.common.security.annotation.EnableHcpFeignClients;
import com.hcp.common.swagger.annotation.EnableCustomSwagger2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 监控中心
 *
 * @author vctgo
 */
@Slf4j
@EnableCustomConfig
@EnableCustomSwagger2
@EnableHcpFeignClients
@SpringBootApplication
@EnableScheduling
public class HcpMonitorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(HcpMonitorApplication.class, args);
        log.info(" (^^)／▽ ▽＼(^^)------hcp监控中心模块启动成功---(^_^)／★＼(^_^) \n");
    }
}
