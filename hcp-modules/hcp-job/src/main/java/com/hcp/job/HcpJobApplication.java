package com.hcp.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.hcp.common.security.annotation.EnableCustomConfig;
import com.hcp.common.security.annotation.EnableHcpFeignClients;
import com.hcp.common.swagger.annotation.EnableCustomSwagger2;

@Slf4j
@EnableCustomConfig
@EnableCustomSwagger2
@EnableHcpFeignClients
@SpringBootApplication
public class HcpJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcpJobApplication.class, args);
        log.info(" (^^)／▽ ▽＼(^^)------hcp定时任务模块启动成功---(^_^)／★＼(^_^) \n");
    }

}
