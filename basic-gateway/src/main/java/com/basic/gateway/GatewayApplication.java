package com.basic.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: Mr.zhang
 * @Date: 2021/9/20 12:52
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        System.out.println(SpringApplication.run(GatewayApplication.class,args));
    }
}
