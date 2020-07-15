package com.tcsquad.ilogistics.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class Receiver {
    @RabbitListener(queues = "test")
    public void onMessage(String message){
        System.out.println("Received:"+message);
    }
}
