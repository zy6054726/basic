package com.basic.rabbit.producer.rabbit.service;

import com.basic.commons.ReturnResult;

/**
 * @author: Mr.zhang
 * @Date: 2021/8/14 16:02
 */
public interface IMessageProvider {
    public <T> ReturnResult send(T t);
}
