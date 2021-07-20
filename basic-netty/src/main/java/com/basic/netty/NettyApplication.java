package com.basic.netty;

import com.basic.commons.enums.ThreadEnums;
import com.basic.commons.thread.ThreadPoolUtils;
import com.basic.netty.server.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * @author: Mr.zhang
 * @Date: 2021/7/19 19:25
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NettyApplication {

    @Resource
    private NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class, args);
    }

    private ThreadPoolUtils threadPoolUtils = new ThreadPoolUtils(ThreadEnums.FIXED_THREAD, 4, 0, 0L);

    @PostConstruct
    public void init() {
        threadPoolUtils.execute(() -> nettyServer.start());
    }
}
