//package com.basic.netty.config;
//
//import com.alibaba.fastjson.JSON;
//import com.basic.commons.ReturnResult;
//import com.basic.netty.model.Message;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.ByteBufUtil;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.MessageToByteEncoder;
//import io.netty.handler.codec.MessageToMessageEncoder;
//import lombok.extern.slf4j.Slf4j;
//
//import java.nio.CharBuffer;
//import java.nio.charset.Charset;
//import java.util.List;
//
///**
// * 自定义编码器
// * @author: Mr.zhang
// * @Date: 2021/7/19 19:57
// */
//@Slf4j
//public class NettyMessageEncode extends MessageToByteEncoder<ReturnResult> {
//
//    @Override
//    protected void encode(ChannelHandlerContext channelHandlerContext, ReturnResult returnResult, ByteBuf byteBuf) throws Exception {
//        // 转换为JSON
//        String json = JSON.(returnResult) + "\n";
//        log.info(json);
//        byteBuf.writeBytes()
//
//        list.add(ByteBufUtil.encodeString(channelHandlerContext.alloc(), CharBuffer.wrap(json), Charset.defaultCharset()));
//
//    }
//
//}
