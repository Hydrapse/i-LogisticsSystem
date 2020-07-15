package com.tcsquad.ilogistics.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    @Bean
    public Queue testQueue() {
        return new Queue("test");
    }

//    @Bean
//    TopicExchange testTopicExchange() {
//        return new TopicExchange("test-exchange");
//    }
//
//    @Bean
//    Binding testBinding(Queue testQueue, TopicExchange testTopicExchange) {
//        return BindingBuilder.bind(testQueue).to(testTopicExchange).with("test.#");
//    }

    @Bean
    public Queue orderQueue() {
        return new Queue("order",true,false,false);
    }
    @Bean
    public Queue unreviewedOrderQueue() {
        return new Queue("unreviewed order",true,false,false);
    }
    @Bean
    public Queue unreviewedReturnedItemQueue() {
        return new Queue("unreviewed returned item",true,false,false);
    }
    @Bean
    public Queue unreviewedExchangeItemQueue() {
        return new Queue("unreviewed exchange item",true,false,false);
    }
    @Bean
    public Queue unreviewedItemOutQueue() {
        return new Queue("unreviewed item out",true,false,false);
    }
    @Bean
    public Queue unreviewedItemInQueue() {
        return new Queue("unreviewed item in",true,false,false);
    }
    @Bean
    public Queue itemInQueue() {
        return new Queue("item in",true,false,false);
    }
    @Bean
    public Queue lackItemQueue() {
        return new Queue("lack item",true,false,false);
    }
}
