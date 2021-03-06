package com.itchain.msalib.eventstore;

import com.itchain.msalib.MspLibApplication;
import com.itchain.msalib.eventstore.domain.EntityWithIdAndEventList;
import com.itchain.msalib.eventstore.domain.MongoClient;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MspLibApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@ComponentScan()
public class MongodbStoreTest {
    @Autowired
    MongodbStore store;
    @Autowired
    MongoClient client;

    @After
    public void cleanUp() {
        client.deleteAll();
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        SampleEvent event01 = new SampleEvent("event01");

        EntityWithIdAndEventList set = store.save("event01", event01);

        EntityWithIdAndEventList result = client.findById(set.getId()).get();

        assertEquals(1, set.getEventList().size());
        assertEquals(set.getId(), result.getId());
    }

    @Test
    public void load_basic() {
        SampleEvent event01 = new SampleEvent("event01");

        EntityWithIdAndEventList set = store.save("1", event01);

        EntityWithIdAndEventList result = store.load(set.getId());

        assertEquals(set.getId(), result.getId());
        assertEquals(1, result.getEventList().size());
    }


}
