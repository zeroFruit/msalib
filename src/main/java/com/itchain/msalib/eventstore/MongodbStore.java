package com.itchain.msalib.eventstore;

import com.itchain.msalib.common.Event;
import com.itchain.msalib.eventstore.domain.EntityWithIdAndEventList;
import com.itchain.msalib.eventstore.domain.MongoClient;
import com.itchain.msalib.eventstore.domain.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class MongodbStore implements Store {
    @Autowired
    MongoClient client;

    @Override
    public EntityWithIdAndEventList save(String aggregateID, Event event) {
        EntityWithIdAndEventList entity = load(aggregateID);
        if (entity == null) {
            entity = new EntityWithIdAndEventList();
        }

        entity.addEvent(event);

        return client.save(entity);
    }

    @Override
    public EntityWithIdAndEventList load(String aggregateID) {
        Optional<EntityWithIdAndEventList> option = client.findById(aggregateID);
        if (!option.isPresent()) {
            return null;
        }
        return option.get();
    }
}
