package com.itchain.msalib.common;


import java.util.List;

public interface EventRepository {
    void save(Event event);
    List<Event> load(String id);
}
