package com.itchain.msalib.eventstore;

import com.itchain.msalib.common.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class SampleEvent implements Event {
    @NonNull
    private String id;

    @Override
    public String getID() {
        return id;
    }
}
