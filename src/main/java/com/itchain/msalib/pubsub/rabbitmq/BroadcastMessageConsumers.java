package com.itchain.msalib.pubsub.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BroadcastMessageConsumers {
    private static final Logger logger = LoggerFactory.getLogger(BroadcastMessageConsumers.class);

    @RabbitListener(queues = {BroadcastConfig.FANOUT_QUEUE1})
    public void receiveMessageFromQueue1(String message) {
        logger.info("Received queue 1 message: " + message);
    }

    @RabbitListener(queues = {BroadcastConfig.FANOUT_QUEUE2})
    public void receiveMessageFromQueue2(String message) {
        logger.info("Received queue 2 message: " + message);
    }
}
