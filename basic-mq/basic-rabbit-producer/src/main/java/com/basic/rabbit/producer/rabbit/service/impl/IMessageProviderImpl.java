package com.basic.rabbit.producer.rabbit.service.impl;

import com.basic.rabbit.producer.rabbit.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author: Mr.zhang
 * @Date: 2021/8/14 16:02
 */
@Service
@EnableBinding(Source.class)
public class IMessageProviderImpl implements IMessageProvider {
    @Resource
    private MessageChannel output;
    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("serial = " + serial);
        return serial;
    }
}
