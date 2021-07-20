package com.basic.netty.server;

import com.basic.netty.config.NettyMessageDecode;
import com.basic.netty.config.NettyMessageEncode;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

/**
 * 服务端初始化，客户端与服务器端连接一旦创建，这个类中方法就会被回调，设置出站编码器和入站解码器
 * @author: Mr.zhang
 * @Date: 2021/7/19 19:33
 */
@Component
public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final int messageMaxLength = 1024;

//    @Override
//    protected void initChannel(Channel channel) throws Exception {
//        channel.pipeline()
//                //添加编码器
//                .addLast(new NettyMessageEncode())
//                //添加Netty 自带的 换行解码器（用来解决 沾包，拆包） 详细见 https://juejin.im/post/5b67902f6fb9a04fc67c1a24
//                .addLast(new LineBasedFrameDecoder(messageMaxLength))
//                //添加自定义的 解码器
//                .addLast( new NettyMessageDecode())
//                //添加 接收消息的 处理器
//                .addLast(new NettyServerHandler());
//    }


        @Override
    protected void initChannel(SocketChannel channel) throws Exception {
//        channel.pipeline().addLast("decoder",new StringDecoder(CharsetUtil.UTF_8));
//        channel.pipeline().addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));
            channel.pipeline().addLast(new NettyMessageEncode());
            channel.pipeline().addLast(new NettyMessageDecode());
            channel.pipeline().addLast(new LineBasedFrameDecoder(messageMaxLength));
        channel.pipeline().addLast(new NettyServerHandler());
    }
}
