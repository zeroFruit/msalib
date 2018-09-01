package com.itchain.msalib.pubsub.rabbitmq.sample;

import com.itchain.msalib.common.Event;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class SampleEvent2 implements Event, Serializable {
    @NonNull
    private String id;
    private String something;
    @Override
    public String getID() {
        return this.id;
    }
}
