package com.basic.gateway.zuul.service;

import com.basic.commons.ReturnResult;


/**
 * @author: Mr.zhang
 * @Date: 2021/7/9 17:16
 */
public interface ZuulService {
    /**
     * 刷新路由
     */
    ReturnResult refreshRoute();
}
