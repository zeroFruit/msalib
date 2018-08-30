package com.itchain.msalib.eventstore.exception;

public class EventIDEmptyException extends EventStoreException{
    public EventIDEmptyException() {
        super("Event ID is empty");
    }
}
