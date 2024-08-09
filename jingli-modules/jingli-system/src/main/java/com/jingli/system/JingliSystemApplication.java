package com.jingli.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jingli.common.security.annotation.EnableCustomConfig;
import com.jingli.common.security.annotation.EnableRyFeignClients;
import com.jingli.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 系统模块
 * 
 * @author jingli
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class JingliSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(JingliSystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
