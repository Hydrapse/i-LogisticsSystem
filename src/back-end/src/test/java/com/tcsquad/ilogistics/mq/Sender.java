package com.tcsquad.ilogistics.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        String context = "hello from" + new Date();
        amqpTemplate.convertAndSend("test",context);
        System.out.println("sent:"+context);
    }
}
