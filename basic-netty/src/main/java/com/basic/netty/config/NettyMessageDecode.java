//package com.basic.netty.config;
//
//import com.basic.netty.model.Message;
//import com.basic.netty.model.MessageTypeEnum;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.ByteToMessageDecoder;
//import io.netty.handler.codec.TooLongFrameException;
//import lombok.extern.slf4j.Slf4j;
//
//import java.nio.charset.Charset;
//import java.util.List;
//
///**
// * 自定义解码器
// * @author: Mr.zhang
// * @Date: 2021/7/19 20:07
// */
//@Slf4j
//public class NettyMessageDecode extends ByteToMessageDecoder{
//
//    @Override
//    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
//        Message message = new Message();
//        message.setMagicNumber(byteBuf.readInt());  // 读取魔数
//        message.setMainVersion(byteBuf.readByte()); // 读取主版本号
//        message.setSubVersion(byteBuf.readByte()); // 读取次版本号
//        message.setModifyVersion(byteBuf.readByte());	// 读取修订版本号
//        CharSequence sessionId = byteBuf.readCharSequence(
//                0, Charset.defaultCharset());	// 读取sessionId
//        message.setSessionId((String)sessionId);
//
//        message.setMessageType(MessageTypeEnum.get(byteBuf.readByte()));	// 读取当前的消息类型
//        short attachmentSize = byteBuf.readShort();	// 读取附件长度
//        for (short i = 0; i < attachmentSize; i++) {
//            int keyLength = byteBuf.readInt();	// 读取键长度和数据
//            CharSequence key = byteBuf.readCharSequence(keyLength, Charset.defaultCharset());
//            int valueLength = byteBuf.readInt();	// 读取值长度和数据
//            CharSequence value = byteBuf.readCharSequence(valueLength, Charset.defaultCharset());
//            message.addAttachment(key.toString(), value.toString());
//        }
//
//        int bodyLength = byteBuf.readInt();	// 读取消息体长度和数据
//        CharSequence body = byteBuf.readCharSequence(bodyLength, Charset.defaultCharset());
//        message.setBody(body.toString());
//        out.add(message);
//    }
//
////    //    @Override
//////    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> list) throws Exception {
//////        String message = msg.toString(Charset.defaultCharset());
//////        log.info(message);
//////        ReturnResult returnResult = JSON.parseObject(message, ReturnResult.class);
//////        list.add(returnResult);
//////    }
////
////    private static int HEADER_SIZE = 4;
////    private final ByteBuf[] delimiters;
////    private final int maxFrameLength;
////    private final boolean stripDelimiter=true;
////    private final boolean failFast=true;
////    private boolean discardingTooLongFrame;
////    private static ByteBuf buf = Unpooled.buffer();
////    private int tooLongFrameLength;
////
////    public NettyMessageDecode(int maxFrameLength,ByteBuf delimiter ) {
////        this(maxFrameLength,new ByteBuf[]{delimiter.slice(delimiter.readerIndex(), delimiter.readableBytes())});
////    }
////
////    public NettyMessageDecode(int maxFrameLength, ByteBuf... delimiters ) {
////        if(delimiters == null) {
////            throw new NullPointerException("delimiters");
////        } else if(delimiters.length == 0) {
////            throw new IllegalArgumentException("empty delimiters");
////        } else {
////            this.delimiters = new ByteBuf[delimiters.length];
////            for (int i = 0; i < delimiters.length; ++i) {
////                ByteBuf d = delimiters[i];
////                validateDelimiter(d);
////                this.delimiters[i] = d.slice(d.readerIndex(), d.readableBytes());
////            }
////            this.maxFrameLength = maxFrameLength;
////        }
////    }
////
////    @Override
////    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
////        Object decoded = this.decode(ctx, in);
////        if(decoded != null) {
////            out.add(decoded);
////        }
////    }
////
////    protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer) {
////        try{
////            int minFrameLength = 2147483647;
////            ByteBuf minDelim = null;
////            ByteBuf[] minDelimLength = this.delimiters;
////            int frame = minDelimLength.length;
////            int tooLongFrameLength;
////            for(tooLongFrameLength = 0; tooLongFrameLength < frame; ++tooLongFrameLength) {
////                ByteBuf delim = minDelimLength[tooLongFrameLength];
////                int frameLength = indexOf(buffer, delim);
////                if(frameLength >= 0 && frameLength < minFrameLength) {
////                    minFrameLength = frameLength;
////                    minDelim = delim;
////                }
////            }
////            if(minDelim != null) {
////                int var10 = minDelim.capacity();
////                if(this.discardingTooLongFrame) {
////                    this.discardingTooLongFrame = false;
////                    buffer.skipBytes(minFrameLength + var10);
////                    tooLongFrameLength = this.tooLongFrameLength;
////                    this.tooLongFrameLength = 0;
////                    if(!this.failFast) {
////                        this.fail((long)tooLongFrameLength);
////                    }
////                    return null;
////                } else if(minFrameLength > this.maxFrameLength) {
////                    buffer.skipBytes(minFrameLength + var10);
////                    this.fail((long)minFrameLength);
////                    return null;
////                } else {
////                    //如果buffer的中小于5表达根本不可能有头的位置，所以跳过
////                    if(buffer.readableBytes()<=5){return null;}
////                    int bodyLength=bodyLenght(buffer);
////                    ByteBuf var11;
////                    if((bodyLength+5)<=buffer.readableBytes()){
////                        if(this.stripDelimiter) {
////                            buffer.skipBytes(minFrameLength+5);
////                            var11 = buffer.readRetainedSlice(bodyLength);
////                        } else {
////                            buffer.skipBytes(5);
////                            var11 = buffer.readRetainedSlice(bodyLength + var10);
////                            buffer.skipBytes(bodyLength);
////                        }
////                    }else{
////                        return null;
////                    }
////                    return var11;
////                }
////            } else {
////                if(!this.discardingTooLongFrame) {
////                    if(buffer.readableBytes() > this.maxFrameLength) {
////                        this.tooLongFrameLength = buffer.readableBytes();
////                        buffer.skipBytes(buffer.readableBytes());
////                        this.discardingTooLongFrame = true;
////                        if(this.failFast) {
////                            this.fail((long)this.tooLongFrameLength);
////                        }
////                    }
////                } else {
////                    this.tooLongFrameLength += buffer.readableBytes();
////                    buffer.skipBytes(buffer.readableBytes());
////                }
////                return null;
////            }
////        }catch (Exception e){
////            e.printStackTrace();
////            return null;
////        }
////    }
////
////
////    private static int indexOf(ByteBuf haystack, ByteBuf needle) {
////        for(int i = haystack.readerIndex(); i < haystack.writerIndex(); ++i) {
////            int haystackIndex = i;
////            int needleIndex;
////            for(needleIndex = 0; needleIndex < needle.capacity() && haystack.getByte(haystackIndex) == needle.getByte(needleIndex); ++needleIndex) {
////                ++haystackIndex;
////                if(haystackIndex == haystack.writerIndex() && needleIndex != needle.capacity() - 1) {
////                    return -1;
////                }
////            }
////            if(needleIndex == needle.capacity()) {
////                return i - haystack.readerIndex();
////            }
////        }
////
////        return -1;
////    }
////
////    /**
////     * 功能：获取信息头中的长度
////     * */
////    private  int bodyLenght(ByteBuf buf){
////        int bodyLength=0;
////        int headerIndex=buf.readerIndex()+1,headerEnd=buf.readerIndex()+4;
////        for(;headerIndex<=headerEnd;headerIndex++){
////            bodyLength*=10;
////            bodyLength+=(int)buf.getByte(headerIndex)-48;
////        }
////        return bodyLength;
////    }
////
////    private void fail(long frameLength) {
////        if(frameLength > 0L) {
////            throw new TooLongFrameException("frame length exceeds " + this.maxFrameLength + ": " + frameLength + " - discarded");
////        } else {
////            throw new TooLongFrameException("frame length exceeds " + this.maxFrameLength + " - discarding");
////        }
////    }
////
////    private static void validateDelimiter(ByteBuf delimiter) {
////        if(delimiter == null) {
////            throw new NullPointerException("delimiter");
////        } else if(!delimiter.isReadable()) {
////            throw new IllegalArgumentException("empty delimiter");
////        }
////    }
//
//}
