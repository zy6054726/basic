package com.basic.netty.model;

import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/21 14:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocketClient implements Serializable {
    private static final long serialVersionUID = 5039283589498123938L;

    /**
     * 客户端连接分组
     */
    private EventLoopGroup group;

    /**
     * 通道
     */
    private ChannelFuture channelFuture;
}
