package com.jingli.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.jingli.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 文件服务
 * 
 * @author jingli
 */
@EnableCustomSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JingliFileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(JingliFileApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  文件服务模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
