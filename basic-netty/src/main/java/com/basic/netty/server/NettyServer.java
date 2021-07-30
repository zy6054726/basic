package com.basic.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.net.InetSocketAddress;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/19 19:32
 */
@Component
@Slf4j
public class NettyServer {

    @Value("${netty.port}")
    private Integer port;

    /**
     * boss 线程组用于处理连接工作
     */
    private EventLoopGroup boss = new NioEventLoopGroup();
    /**
     * work 线程组用于数据处理
     */
    private EventLoopGroup work = new NioEventLoopGroup();

    @Autowired
    private NettyServerChannelInitializer nettyServerChannelInitializer;

    public void start() {
        //配置服务端的NIO线程组
        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(boss, work)  // 绑定线程池
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(nettyServerChannelInitializer)//编码解码
                    .option(ChannelOption.SO_BACKLOG, 1024)//服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝
                    //设置TCP长连接,一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                    .childOption(ChannelOption.SO_KEEPALIVE, true);  //保持长连接，2小时无数据激活心跳机制
            // 绑定端口，开始接收进来的连接
            ChannelFuture future = bootstrap.bind().sync();
            if (future.isSuccess()) {
                log.info("启动 Netty Server");
            }
            //关闭channel和块，直到它被关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

//    /**
//     * SpringBoot 销毁的时候 调用
//     * @throws InterruptedException
//     */
//    @PreDestroy
//    public void destory() throws InterruptedException {
//        boss.shutdownGracefully().sync();
//        work.shutdownGracefully().sync();
//        log.info("关闭Netty");
//    }
}
