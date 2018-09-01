package com.itchain.msalib.pubsub.rabbitmq.sample;

import com.itchain.msalib.eventstore.EventStorageService;
import com.itchain.msalib.pubsub.rabbitmq.BroadcastMessageProducer;
import com.itchain.msalib.pubsub.rabbitmq.sample.SampleEvent;
import com.itchain.msalib.pubsub.rabbitmq.sample.SampleEvent2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BroadcastMessageController {
    private final BroadcastMessageProducer messageProducer;
    private final EventStorageService store;

    @Autowired
    public BroadcastMessageController(EventStorageService store, BroadcastMessageProducer messageProducer) {
        this.messageProducer = messageProducer;
        this.store = store;
    }

    @GetMapping(value = "/")
    public String test() {
        return "Hello lib";
    }

    @PostMapping(value="/broadcast")
    @ResponseStatus(value= HttpStatus.CREATED)
    public void sendMessages(@RequestBody String message) {
        messageProducer.sendMessages(message);
    }

    @PostMapping(value="/sample")
    @ResponseStatus(value= HttpStatus.CREATED)
    public void sendEvent(@RequestBody SampleEvent event) {
        System.out.println("event: " + event);
        messageProducer.publishEvent(event);
    }

    @PostMapping(value="/sample2")
    @ResponseStatus(value= HttpStatus.CREATED)
    public void sendEvent(@RequestBody SampleEvent2 event) {
        System.out.println("event2: " + event);
        messageProducer.publishEvent(event);
    }

    @PostMapping(value="/db/sample")
    @ResponseStatus(value= HttpStatus.CREATED)
    public void sendEventToDB(@RequestBody SampleEvent event) {
        System.out.println("event: " + event);
        store.save(event);
    }
}
