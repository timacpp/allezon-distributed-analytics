package com.allezon.core.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Repository;

import com.aerospike.client.Bin;
import com.aerospike.client.Operation;
import com.allezon.core.domain.aggregates.Aggregate;

@Repository
public class AggregatesDao extends AerospikeDao {
    private static final String SET = "aggregates";
    private static final String COUNT_BIN = "count";
    private static final String SUM_BIN = "sum";

    public AggregatesDao() {
        super(SET);
    }

    public List<Aggregate> batchGet(List<String> keys) {
        return StreamUtils.zip(keys.stream(), getRecords(keys).stream(),
                        (key, record) -> new Aggregate(key, record.getLong(SUM_BIN), record.getLong(COUNT_BIN)))
                .toList();
    }

    public void batchSave(List<Aggregate> aggregates) {
        List<String> keys = aggregates.stream().map(Aggregate::hash).toList();
        List<Operation> operations = aggregates.stream().flatMap(aggregate -> Stream.of(
                    Operation.add(new Bin(COUNT_BIN, aggregate.count())),
                    Operation.add(new Bin(SUM_BIN, aggregate.sum())))
                ).toList();
        operate(keys, operations);
    }
}
