package com.basic.rabbit.producer.rabbit.controller;

import com.basic.commons.ReturnResult;
import com.basic.rabbit.producer.rabbit.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Mr.zhang
 * @Date: 2021/8/14 16:04
 */
@RestController
@RequestMapping("/mq")
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping("/")
    public <T> ReturnResult send(@RequestBody T t) {
        return messageProvider.send(t);
    }
}
