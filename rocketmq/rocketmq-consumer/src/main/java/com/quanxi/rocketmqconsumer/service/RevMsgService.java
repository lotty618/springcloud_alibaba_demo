package com.quanxi.rocketmqconsumer.service;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(consumerGroup = "test-group", topic = "test-topic")
public class RevMsgService implements RocketMQListener {

    @Override
    public void onMessage(Object o) {
        System.out.println("收到消息：" + o.toString());
    }
}
