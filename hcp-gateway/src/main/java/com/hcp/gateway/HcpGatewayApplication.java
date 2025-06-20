package com.hcp.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * 网关启动程序
 *
 * @author vctgo
 */
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HcpGatewayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(HcpGatewayApplication.class, args);
        log.info(" (^^)／▽ ▽＼(^^)------hcp网关启动成功---(^_^)／★＼(^_^) \n");
    }
}
