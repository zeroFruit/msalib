package com.itchain.msalib.eventstore;

import com.itchain.msalib.common.Event;
import com.itchain.msalib.common.EventRepository;
import com.itchain.msalib.eventstore.domain.EntityWithIdAndEventList;
import com.itchain.msalib.eventstore.domain.Store;
import com.itchain.msalib.eventstore.exception.EventIDEmptyException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventStorageService implements EventRepository {
    private Store store;

    public EventStorageService(Store store) {
        this.store = store;
    }

    @Override
    public void save(Event event) {
        if (isEventIDEmpty(event)) {
            throw new EventIDEmptyException();
        }

        store.save(event.getID(), event);
    }

    private boolean isEventIDEmpty(Event event) {
        String eventID = event.getID();
        return eventID == null || eventID.isEmpty();
    }

    @Override
    public List<Event> load(String id) {
        EntityWithIdAndEventList entity = store.load(id);
        return entity.getEventList();
    }
}
