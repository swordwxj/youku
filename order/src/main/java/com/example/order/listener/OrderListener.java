package com.example.order.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @JmsListener(destination = "first")
public void listen(String  json){
    System.out.println("收到json："+json);
}

}
