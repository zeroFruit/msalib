package com.itchain.msalib.pubsub.rabbitmq.sample;

import com.itchain.msalib.common.Event;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@RequiredArgsConstructor
@ToString
public class SampleEvent implements Event, Serializable {
    @NonNull
    private String id;
    @Override
    public String getID() {
        return this.id;
    }
}
