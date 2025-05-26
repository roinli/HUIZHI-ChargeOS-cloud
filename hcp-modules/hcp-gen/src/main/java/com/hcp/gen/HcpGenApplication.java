package com.hcp.gen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.hcp.common.security.annotation.EnableCustomConfig;
import com.hcp.common.security.annotation.EnableHcpFeignClients;
import com.hcp.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 *
 * @author vctgo
 */
@Slf4j
@EnableCustomConfig
@EnableCustomSwagger2
@EnableHcpFeignClients
@SpringBootApplication
public class HcpGenApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(HcpGenApplication.class, args);
        log.info(" (^^)／▽ ▽＼(^^)------hcp代码生成模块启动成功---(^_^)／★＼(^_^) \n");
    }
}
