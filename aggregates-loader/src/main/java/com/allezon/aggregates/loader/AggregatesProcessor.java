package com.allezon.aggregates.loader;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.allezon.core.dao.AggregatesDao;
import com.allezon.core.domain.aggregate.Aggregate;
import com.allezon.core.domain.tag.UserTag;

public class AggregatesProcessor implements Processor<String, UserTag, String, Aggregate> {
    private static final int MAX_REQUESTS_PER_SAVE = 5000;

    private static final Logger logger = LoggerFactory.getLogger(AggregatesProcessor.class);

    private final AggregatesDao aggregatesDao;
    private KeyValueStore<String, Long> countStore;
    private KeyValueStore<String, Long> sumStore;

    public AggregatesProcessor(AggregatesDao aggregatesDao) {
        this.aggregatesDao = aggregatesDao;
    }

    @Override
    public void init(ProcessorContext<String, Aggregate> context) {
        countStore = context.getStateStore("count");
        sumStore = context.getStateStore("sum");

        context.schedule(Duration.ofSeconds(5), PunctuationType.WALL_CLOCK_TIME, timestamp -> {
            long reloadStart = System.currentTimeMillis();
            List<Aggregate> aggregates = new ArrayList<>();
            List<String> processedKeys = new ArrayList<>();

            try (final KeyValueIterator<String, Long> iterator = countStore.all()) {
                while (iterator.hasNext()) {
                    KeyValue<String, Long> entry = iterator.next();
                    Aggregate aggregate = new Aggregate(entry.key, sumStore.get(entry.key), entry.value);

                    processedKeys.add(entry.key);
                    aggregates.add(aggregate);

                    if (aggregates.size() == MAX_REQUESTS_PER_SAVE || !iterator.hasNext()) {
                        aggregatesDao.batchSave(aggregates);
                        aggregates.clear();
                    }
                }
            }

            logger.info("Reloaded aggregates in {} seconds for keys from {} to {}",
                    (System.currentTimeMillis() - reloadStart) / 1000, processedKeys.get(0),
                    processedKeys.get(processedKeys.size() - 1));

            for (String processedKey : processedKeys) {
                sumStore.delete(processedKey);
                countStore.delete(processedKey);
            }
        });
    }

    @Override
    public void process(Record<String, UserTag> record) {
        UserTag tag = record.value();
        Long price = Long.valueOf(tag.product().price());
        String bucket = tag.time().substring(0, 1 + tag.time().lastIndexOf(":")) + "00";
        String keyPrefix = bucket + ":" + tag.action();

        for (String keySuffix : generatePossibleQueryFilterKeys(tag)) {
            String key = keyPrefix + ":" + keySuffix;
            countStore.put(key, 1 + Optional.ofNullable(countStore.get(key)).orElse(0L));
            sumStore.put(key, price + Optional.ofNullable(sumStore.get(key)).orElse(0L));
        }
    }

    private List<String> generatePossibleQueryFilterKeys(UserTag tag) {
        return List.of(
                String.join(":", tag.origin(), tag.product().brandId(), tag.product().categoryId()),
                String.join(":", "null", tag.product().brandId(), tag.product().categoryId()),
                String.join(":", tag.origin(), "null", tag.product().categoryId()),
                String.join(":", tag.origin(), tag.product().brandId(), "null"),
                String.join(":", "null", "null", tag.product().categoryId()),
                String.join(":", tag.origin(), "null", "null"),
                String.join(":", "null", tag.product().brandId(), "null"),
                String.join(":", "null", "null", "null")
        );
    }

}
