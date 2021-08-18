package com.basic.rabbit.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: Mr.zhang
 * @Date: 2021/8/12 9:54
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RabbitProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitProducerApplication.class, args);
    }
}
