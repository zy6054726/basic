package com.basic.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关过滤，操作toekn传递
 * @author: Mr.zhang
 * @Date: 2021/9/20 13:22
 */
@Slf4j
@Component
public class GatewayClientFilter  implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request=exchange.getRequest();
        //获取token
        String authoriztion=request.getHeaders().getFirst("Authorization");
        ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
        builder.header("Authorization", authoriztion);
        return chain.filter(exchange.mutate().request(builder.build()).build());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
