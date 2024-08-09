package com.jingli.admin;

import com.jingli.common.security.annotation.EnableCustomConfig;
import com.jingli.common.security.annotation.EnableRyFeignClients;
import com.jingli.common.swagger.annotation.EnableCustomSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 系统模块
 *
 * @author jingli
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
@EnableWebMvc
@MapperScan("com/jingli/admin/mapper")
public class JingliAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(JingliAdminApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  后台模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
