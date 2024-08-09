package com.jingli.inter;

import com.jingli.common.security.annotation.EnableCustomConfig;
import com.jingli.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jingli.common.swagger.annotation.EnableCustomSwagger2;


@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class JingliInterApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(JingliInterApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  小程序接口模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
