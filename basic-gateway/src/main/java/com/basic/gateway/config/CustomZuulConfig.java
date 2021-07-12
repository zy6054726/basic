package com.basic.gateway.config;

import com.basic.gateway.dynamicrouting.CustomRouteLocator;
import com.basic.gateway.zuul.mapper.GatewayApiDefineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/9 10:41
 */
@Component
public class CustomZuulConfig {
    @Autowired
    ZuulProperties zuulProperties;
    @Autowired
    DispatcherServletPath server;
//    ServerProperties server; getServerHeader



    @Resource
    private GatewayApiDefineMapper gatewayApiDefineMapper;

    @Bean
    @PostConstruct
    public CustomRouteLocator routeLocator() {
//        getServletPrefix
        CustomRouteLocator customRouteLocator = new CustomRouteLocator(this.server.getPrefix(),
                this.zuulProperties,this.gatewayApiDefineMapper);
        return customRouteLocator;
    }
}
