package com.quanxi.nacos_client.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class Test1Controller {
    @Bean(name = "restTemplate")
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/test1")
    String test1() {
        //此处会报错：uri(nacos_server:port)
        //使用@LoadBalanced注解实现负载均衡的时候，服务名称不能带有下划线
        String ret = restTemplate().getForObject("http://nacos_server:8081/test?name=" + "Pony Ma", String.class);
        return "rpc " + ret;
    }

    @GetMapping("/test11")
    String test2(@Qualifier("restTemplate") RestTemplate restTemplate) {
        //此处会报错：uri(nacos_server:port)
        //使用@LoadBalanced注解实现负载均衡的时候，服务名称不能带有下划线
        String ret = restTemplate.getForObject("http://nacos_server:8081/test?name=" + "Pony Ma", String.class);
        return "rpc " + ret;
    }
}
