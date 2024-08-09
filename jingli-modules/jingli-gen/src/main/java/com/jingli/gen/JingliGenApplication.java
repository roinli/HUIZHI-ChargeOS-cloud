package com.jingli.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jingli.common.security.annotation.EnableCustomConfig;
import com.jingli.common.security.annotation.EnableRyFeignClients;
import com.jingli.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 * 
 * @author jingli
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class JingliGenApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(JingliGenApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  代码生成模块启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}
