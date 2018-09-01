package com.itchain.msalib.pubsub.rabbitmq.sample;

import com.itchain.msalib.pubsub.rabbitmq.BroadcastConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {BroadcastConfig.EVENT_QUEUE})
public class SampleEventConsumers {
    private static final Logger logger = LoggerFactory.getLogger(SampleEventConsumers.class);

    @RabbitHandler
    public void receiveEventFromEventQueue(SampleEvent event) {
        logger.info("Received event queue message" + event);
    }

    @RabbitHandler
    public void receiveEvent2FromEventQueue(SampleEvent2 event) {
        logger.info("Received event2 queue message" + event);
    }

}
