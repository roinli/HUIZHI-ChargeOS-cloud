package com.jingli.device.netty;

import com.jingli.common.core.utils.StringUtils;
import com.jingli.common.redis.service.RedisService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
@Slf4j
public class NettyServer {

    @Resource
    RedisService redisService;
    @Value("${netty.port}")
    private Integer nettyPort;


    public void start(InetSocketAddress address) {
        getRedisVal();
//		//boss线程监听端口，worker线程负责数据读写
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 把两个线程组加入进来
            bootstrap.group(boss,worker);
            // 指定使用NioServerSocketChannel这种类型通道
            bootstrap.channel(NioServerSocketChannel.class);
            // 一定要使用去绑定具体的事件处理器
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new StringDecoder());
                    pipeline.addLast(new StringEncoder());
                    pipeline.addLast(new NettyServerInHandler());
                }
            });
            bootstrap.option(ChannelOption.SO_BACKLOG,1024);// 默认128
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);
            bootstrap.childOption(ChannelOption.TCP_NODELAY,true);

            ChannelFuture future = bootstrap.bind(nettyPort).sync();
            log.info("server start ......");
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    private  void getRedisVal(){
        new Thread(() -> {
            while (true) {
                log.info("wait...");
                try {
                    List<String> list =  redisService.getCacheList("NETTY_NOTICE");
//                    List<String> list = RedisUtils.getList("NETTY_NOTICE");
                    HashSet<String> taskSet = new HashSet<>();
                    List<String> taskList = new ArrayList<String>();
                    for (int j = 0; j < list.size(); j++) {
                        String msg = list.get(j);
                        if(StringUtils.isNotEmpty(msg)){
                            try {
                                String arr[] = msg.split(",");
                                String companyId = arr[2];
                                String gfNum = arr[3];
                                String packageType = arr[4];
                                String toUser = companyId + "&" + gfNum  + "&" +packageType + "&";
                                if(taskSet.contains(toUser)){
//										logger.debug("防止设备压力过大，等待下次执行：" + msg);
                                    continue;
                                }else{
                                    taskSet.add(toUser);
                                    taskList.add(msg);
//                                    com.jingli.netty.RedisUtils.updateList("NETTY_NOTICE", j, "del");
                                    redisService.setCacheList("NETTY_NOTICE",taskList);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
//                    com.jingli.netty.RedisUtils.deleteList("NETTY_NOTICE");
                    redisService.deleteObject("NETTY_NOTICE");

                    for (String msg : taskList) {
                        NettyServerInHandler.sendToDevice(msg);
                    }
                    Thread.sleep(1000);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
