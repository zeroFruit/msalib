package com.itchain.msalib.pubsub.rabbitmq;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class BroadcastConfig {
    public static final String FANOUT_QUEUE1 = "sample_msa/fanout_queue1";
    public static final String FANOUT_QUEUE2 = "sample_msa/fanout_queue2";
    public static final String EXCHANGE = "sample_msa/exchange";

    public static final String EVENT_QUEUE = "event_queue";
    public static final String EVENT_EXCHANGE = "event_exchange";

    @Bean
    public List<Declarable> fanoutBindings() {
        Queue fanoutQueue1 = new Queue(FANOUT_QUEUE1, false);
        Queue fanoutQueue2 = new Queue(FANOUT_QUEUE2, false);
        FanoutExchange exchange = new FanoutExchange(EXCHANGE);

        return Arrays.asList(
                fanoutQueue1,
                fanoutQueue2,
                exchange,
                BindingBuilder.bind(fanoutQueue1).to(exchange),
                BindingBuilder.bind(fanoutQueue2).to(exchange)
        );
    }

    @Bean
    public List<Declarable> eventBindings() {
        Queue eventQueue = new Queue(EVENT_QUEUE, false);
        FanoutExchange eventExchange = new FanoutExchange(EVENT_EXCHANGE);

        return Arrays.asList(
                eventQueue,
                eventExchange,
                BindingBuilder.bind(eventQueue).to(eventExchange)
        );
    }

    @Bean
    public SimpleRabbitListenerContainerFactory container(ConnectionFactory connectionFactory, SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}
