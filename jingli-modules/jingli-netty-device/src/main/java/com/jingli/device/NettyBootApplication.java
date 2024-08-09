package com.jingli.device;

import com.jingli.device.netty.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jingli.common.security.annotation.EnableCustomConfig;
import com.jingli.common.security.annotation.EnableRyFeignClients;
import com.jingli.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.net.InetSocketAddress;

/**
 *
 * @author jingli
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class NettyBootApplication implements CommandLineRunner
{
    @Autowired
    private NettyServer nettyServer;
    @Value("${netty.port}")
    private Integer nettyPort;


    public static void main(String[] args) {
        SpringApplication.run(NettyBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        InetSocketAddress address = new InetSocketAddress("0.0.0.0",nettyPort);
        nettyServer.start(address);
    }
}
