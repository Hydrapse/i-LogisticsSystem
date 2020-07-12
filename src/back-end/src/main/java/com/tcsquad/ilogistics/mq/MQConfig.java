package com.tcsquad.ilogistics.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    @Bean
    public Queue testQueue() {
        return new Queue("test");
    }
}
