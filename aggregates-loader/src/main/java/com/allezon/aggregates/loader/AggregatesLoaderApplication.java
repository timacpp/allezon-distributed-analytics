package com.allezon.aggregates.loader;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import com.allezon.core.dao.AggregatesDao;

@EnableKafka
@EnableKafkaStreams
@SpringBootApplication
@Import(AggregatesDao.class)
public class AggregatesLoaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregatesLoaderApplication.class, args);
    }

    @Autowired
    public Topology kafkaStreamTopology(StreamsBuilder streamsBuilder, AggregatesDao aggregatesDao) {
        StoreBuilder<KeyValueStore<String, Long>> countStoreBuilder = Stores.keyValueStoreBuilder(
                Stores.persistentKeyValueStore("count"), Serdes.String(), Serdes.Long());
        StoreBuilder<KeyValueStore<String, Long>> sumStoreBuilder = Stores.keyValueStoreBuilder(
                Stores.persistentKeyValueStore("sum"), Serdes.String(), Serdes.Long());

        return streamsBuilder.build()
                .addSource("source", "user-tags")
                .addProcessor("processor", () -> new AggregatesProcessor(aggregatesDao), "source")
                .addStateStore(countStoreBuilder, "processor")
                .addStateStore(sumStoreBuilder, "processor");
    }
}
