package com.itchain.msalib.eventstore.exception;

public class EventStoreException extends RuntimeException {
    public EventStoreException() {
        super();
    }

    public EventStoreException(String message) {
        super(message);
    }
}
