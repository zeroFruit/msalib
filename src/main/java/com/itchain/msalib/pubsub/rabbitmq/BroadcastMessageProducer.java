package com.itchain.msalib.pubsub.rabbitmq;

import com.itchain.msalib.common.Event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BroadcastMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public BroadcastMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessages(String message) {
        rabbitTemplate.convertAndSend(BroadcastConfig.EXCHANGE, "", message);
    }

    public void publishEvent(Event event) {
        rabbitTemplate.convertAndSend(BroadcastConfig.EVENT_EXCHANGE, "", event);
    }
}
