package org.ecommerce.app.config;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableRabbit
@RequiredArgsConstructor
public class RabbitMqConfiguration {

    private final AmqpAdmin rabbitAdmin;
    private final CachingConnectionFactory connectionFactory;

    @Bean
    public Binding declareQueue() {
        String name = "payment-queue";
        String routingKey = "payment-key";
        boolean durable = true;
        boolean exclusive = false;
        boolean autoDelete = false;

        String queueName = rabbitAdmin.declareQueue(new Queue(name, durable, exclusive, autoDelete));

        String exchangeName = "payment-exchange";
        DirectExchange exchange = new DirectExchange(exchangeName, durable, autoDelete);
        rabbitAdmin.declareExchange(exchange);

        Map<String, Object> arguments = null;
        Binding binding = new Binding(queueName, DestinationType.QUEUE, exchangeName, routingKey, arguments);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }

}
