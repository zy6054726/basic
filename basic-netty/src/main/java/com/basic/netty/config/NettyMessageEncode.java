package com.basic.netty.config;

import com.alibaba.fastjson.JSON;
import com.basic.commons.ReturnResult;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 自定义编码器
 * @author: Mr.zhang
 * @Date: 2021/7/19 19:57
 */
@Slf4j
public class NettyMessageEncode extends MessageToByteEncoder<ReturnResult> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ReturnResult returnResult, ByteBuf byteBuf) throws Exception {
        byte[] body = JSON.toJSONBytes(returnResult);  //将对象转换为byte，伪代码，具体用什么进行序列化，你们自行选择。可以使用我上面说的一些
        int dataLength = body.length;  //读取消息的长度
        byteBuf.writeInt(dataLength);  //先将消息长度写入，也就是消息头
        byteBuf.writeBytes(body);  //消息体中包含我们要发送的数据
    }

    //    @Override
//    protected void encode(ChannelHandlerContext channelHandlerContext, ReturnResult returnResult, List<Object> list) throws Exception {
//        // 转换为JSON
//        String json = JSON.toJSONString(returnResult) + "\n";
//        log.info(json);
//        list.add(ByteBufUtil.encodeString(channelHandlerContext.alloc(), CharBuffer.wrap(json), Charset.defaultCharset()));
//    }
}
