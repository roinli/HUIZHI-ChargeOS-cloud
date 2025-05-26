package com.hcp.system;

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
public class HcpSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(HcpSystemApplication.class, args);
        log.info(" (^^)／▽ ▽＼(^^)------hcp系统模块启动成功---(^_^)／★＼(^_^) \n");
    }
}
