package com.basic.rabbit.producer.rabbit.service.impl;

import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import com.basic.commons.enums.MqType;
import com.basic.rabbit.producer.rabbit.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public <T> ReturnResult send(T t) {
        return output.send(MessageBuilder.withPayload(t).build())
                ? new ReturnResult(Flag.SYSTEM_SUCESS_MQ)
                : new ReturnResult(Flag.system_error_mq);
    }
}
