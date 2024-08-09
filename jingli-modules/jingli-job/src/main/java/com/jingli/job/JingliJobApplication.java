package com.jingli.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jingli.common.security.annotation.EnableCustomConfig;
import com.jingli.common.security.annotation.EnableRyFeignClients;
import com.jingli.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 * 
 * @author jingli
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients   
@SpringBootApplication
public class JingliJobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(JingliJobApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  定时任务模块启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}
