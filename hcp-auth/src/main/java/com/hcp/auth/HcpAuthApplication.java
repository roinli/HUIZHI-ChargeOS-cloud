package com.hcp.auth;

import com.hcp.common.security.annotation.EnableCustomConfig;
import com.hcp.common.swagger.annotation.EnableCustomSwagger2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.hcp.common.security.annotation.EnableHcpFeignClients;

/**
 * 认证授权中心
 *
 * @author vctgo
 */
@Slf4j
@EnableHcpFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableCustomConfig
@EnableCustomSwagger2
public class HcpAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(HcpAuthApplication.class, args);
        log.info(" (^^)／▽ ▽＼(^^)------hcp权限启动成功---(^_^)／★＼(^_^) \n");
    }
}
