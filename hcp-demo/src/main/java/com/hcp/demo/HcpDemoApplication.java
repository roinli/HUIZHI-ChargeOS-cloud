package com.hcp.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.hcp.common.security.annotation.EnableCustomConfig;
import com.hcp.common.security.annotation.EnableHcpFeignClients;
import com.hcp.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 系统模块
 *
 * @author vctgo
 */
@Slf4j
@EnableCustomConfig
@EnableCustomSwagger2
@EnableHcpFeignClients
@SpringBootApplication
public class HcpDemoApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(HcpDemoApplication.class, args);
        log.info(" (^^)／▽ ▽＼(^^)------hcp Demo样例模块启动成功---(^_^)／★＼(^_^) \n");
    }
}
