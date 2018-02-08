package com.example.protal.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Resource
    private JmsTemplate jmsTemplate;

    public void sendOrderMessage(String json){
        jmsTemplate.convertAndSend("first",json);
    }
}
