package com.quanxi.nacos_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    @Autowired
    LoadBalancerClient loadBalancerClient;

    /**
     * 功能：通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
     * 使用Spring Cloud Common中的LoadBalancerClient接口来挑选服务实例信息。
     * 然后从挑选出的实例信息中获取可访问的URI，拼接上服务提供方的接口规则来发起调用
     * @return
     */
    @GetMapping("/test")
    String test() {
        ServiceInstance instance = loadBalancerClient.choose("nacos_server");
        String url = instance.getUri() + "/test?name=" + "Jack Ma";
        RestTemplate restTemplate = new RestTemplate();
        String ret = restTemplate.getForObject(url, String.class);
        return "" + url + ", Return: " + ret;
    }
}
