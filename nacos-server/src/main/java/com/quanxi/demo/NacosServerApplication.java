package com.quanxi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient  //微服务注册到服务发现组件上，即让注册中心能发现，扫描到该服务
public class NacosServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosServerApplication.class, args);
    }

    @RestController
    static class TestController {
        @GetMapping("/test")
        public String test(String name) {
            return "hello " + name;
        }
    }
}
