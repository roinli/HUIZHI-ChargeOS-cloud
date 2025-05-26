package com.hcp.mp;

import com.hcp.common.security.annotation.EnableCustomConfig;
import com.hcp.common.security.annotation.EnableHcpFeignClients;
import com.hcp.common.swagger.annotation.EnableCustomSwagger2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 系统模块
 *
 * @author abdl
 */
@Slf4j
@EnableCustomConfig
@EnableHcpFeignClients
@SpringBootApplication
@EnableCustomSwagger2
@EnableScheduling
public class HcpMpApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(HcpMpApplication.class, args);
        log.info(" (^^)／▽ ▽＼(^^)------hcp小程序模块启动成功---(^_^)／★＼(^_^) \n");
    }
}
