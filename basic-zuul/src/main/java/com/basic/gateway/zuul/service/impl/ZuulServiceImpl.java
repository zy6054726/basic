package com.basic.gateway.zuul.service.impl;

import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import com.basic.gateway.zuul.service.ZuulService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/9 17:17
 */
@Service
@Slf4j
public class ZuulServiceImpl implements ZuulService {

    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    RouteLocator routeLocator;

    @Override
    public ReturnResult refreshRoute() {
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        publisher.publishEvent(routesRefreshedEvent);
        return new ReturnResult(Flag.SUCCESS);
    }
}
