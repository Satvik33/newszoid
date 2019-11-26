package com.stackroute.recommendationservice.config;

import com.stackroute.recommendationservice.services.RabbitMqReceiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqPostConfig {

    public static final String topicExchangeName = "post";

    public static final String queueName = "user3";

    @Bean
    Queue queue2() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange2() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding2(@Qualifier("queue2") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue2()).to(exchange2()).with("user.post.#");
    }

    @Bean
    MessageListenerAdapter listenerAdapter2(RabbitMqReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receivePosts");
    }

    @Bean
    SimpleMessageListenerContainer container2(ConnectionFactory connectionFactory,
                                              @Qualifier("listenerAdapter2") MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

}

