package com.quanxi.rocketmqproducer.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/msg")
public class SendMsgController {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/send")
    public String sendMsg() {
        rocketMQTemplate.convertAndSend("test-topic", "Hello RocketMQ! It's for test!");
        return "success";
    }
}
