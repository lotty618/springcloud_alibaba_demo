package com.quanxi.nacos_client.controller;

import com.quanxi.dubboserviceapi.service.ConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test2Controller {
    @org.apache.dubbo.config.annotation.DubboReference
    private ConsumerService consumerService;

    @GetMapping("/test2")
    public String test2() {
        return "Dubbo Msg : " + consumerService.service();
    }
}
