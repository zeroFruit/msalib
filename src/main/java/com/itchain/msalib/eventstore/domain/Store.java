package com.itchain.msalib.eventstore.domain;

import com.itchain.msalib.common.Event;
import org.springframework.stereotype.Component;

@Component
public interface Store {
    EntityWithIdAndEventList save(String aggregateID, Event event);
    EntityWithIdAndEventList load(String aggregateID);
}
