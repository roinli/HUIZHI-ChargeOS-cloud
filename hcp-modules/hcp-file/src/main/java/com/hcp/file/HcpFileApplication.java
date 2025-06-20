package com.hcp.file;

import com.hcp.common.security.annotation.EnableCustomConfig;
import com.hcp.common.security.annotation.EnableHcpFeignClients;
import com.hcp.file.config.SpringFileStorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.hcp.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.context.annotation.Import;

/**
 * 文件服务
 *
 * @author vctgo
 */
@Slf4j
@EnableCustomSwagger2
@SpringBootApplication()
@EnableHcpFeignClients
@EnableCustomConfig
@Import({SpringFileStorageProperties.class})
public class HcpFileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(HcpFileApplication.class, args);
        log.info(" (^^)／▽ ▽＼(^^)------hcp文件服务启动成功---(^_^)／★＼(^_^) \n");
    }
}
