package com.hcp.simulator;

import com.hcp.common.security.annotation.EnableCustomConfig;
import com.hcp.common.security.annotation.EnableHcpFeignClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 系统模块
 *
 * @author abdl
 */
@Slf4j
@EnableCustomConfig
@EnableHcpFeignClients
@SpringBootApplication
public class HcpSimulatorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(HcpSimulatorApplication.class, args);
        log.info(" (^^)／▽ ▽＼(^^)------hcp模拟器模块启动成功---(^_^)／★＼(^_^) \n");
    }
}
