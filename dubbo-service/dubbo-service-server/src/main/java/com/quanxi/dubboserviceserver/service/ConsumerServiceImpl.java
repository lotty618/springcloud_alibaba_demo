package com.quanxi.dubboserviceserver.service;

import com.quanxi.dubboserviceapi.service.ConsumerService;

@org.apache.dubbo.config.annotation.DubboService
public class ConsumerServiceImpl implements ConsumerService {
    @Override
    public String service() {
        return "Consumer Service invoke";
    }
}
