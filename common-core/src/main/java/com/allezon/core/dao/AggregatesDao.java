package com.allezon.core.dao;

import java.util.List;

import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Repository;

import com.aerospike.client.BatchRecord;
import com.aerospike.client.Bin;
import com.aerospike.client.Operation;
import com.allezon.core.domain.aggregate.Aggregate;

@Repository
public class AggregatesDao extends AerospikeDao {
    private static final String SET = "aggregates";
    private static final String COUNT_BIN = "count";
    private static final String SUM_BIN = "sum";

    public AggregatesDao() {
        super(SET);
    }

    public List<Aggregate> batchGet(List<String> keys) {
        return StreamUtils.zip(keys.stream(), getRecords(keys).stream(), (key, record) -> {
            long sum = record != null ?  record.getLong(SUM_BIN) : 0L;
            long count = record != null ? record.getLong(COUNT_BIN) : 0L;
            return new Aggregate(key, sum, count);
        }).toList();
    }

    public void batchSave(List<Aggregate> aggregates) {
        List<BatchRecord> batchRecords = aggregates.stream()
                .map(aggregate -> createBatchWrite(aggregate.hash(),
                        Operation.add(new Bin(COUNT_BIN, aggregate.count())),
                        Operation.add(new Bin(SUM_BIN, aggregate.sum()))))
                .toList();
        operate(batchRecords);
    }
}
