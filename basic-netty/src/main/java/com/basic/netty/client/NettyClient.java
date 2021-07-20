package com.basic.netty.client;

import com.alibaba.fastjson.JSON;
import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import com.basic.netty.config.NettyMessageDecode;
import com.basic.netty.config.NettyMessageEncode;
import com.basic.netty.server.NettyServerChannelInitializer;
import com.basic.netty.server.NettyServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/19 20:58
 */
@Slf4j
public class NettyClient implements Runnable {

    static final String HOST = System.getProperty("host", "192.168.199.136");
    static final int PORT = Integer.parseInt(System.getProperty("port", "3000"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "1024"));

    private String content;

    public NettyClient(String content) {
        this.content = content;
    }

    @Override
    public void run() {
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {

            int num = 0;
            boolean boo =true;

            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new NettyServerChannelInitializer() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast("encoder", new NettyMessageEncode());
                            channel.pipeline().addLast("decoder", new NettyMessageDecode());
                            channel.pipeline().addLast(new LineBasedFrameDecoder(SIZE));
                            channel.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            ChannelFuture future = b.connect(HOST, PORT).sync();

            while (boo) {

                num++;

                future.channel().writeAndFlush(content + "--" + LocalDateTime.now());

                try { //休眠一段时间
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //每一条线程向服务端发送的次数
                if (num == 100) {
                    boo = false;
                }
            }

            log.info(content + "-----------------------------" + num);
            //future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    /**
     *  下面是不加线程的
     */
    public static void main(String[] args) throws Exception {

        sendMessage(JSON.toJSONString(new ReturnResult<>(Flag.SUCCESS)));
    }

    public static void sendMessage(String content) throws InterruptedException {
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new NettyServerChannelInitializer() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new NettyMessageEncode());
                            channel.pipeline().addLast(new NettyMessageDecode());
                            channel.pipeline().addLast(new LineBasedFrameDecoder(SIZE));
                            channel.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            ChannelFuture future = b.connect(HOST, PORT).sync();
            future.channel().writeAndFlush(content);
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
