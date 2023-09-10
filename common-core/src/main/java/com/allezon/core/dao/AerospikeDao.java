package com.allezon.core.dao;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.BatchRecord;
import com.aerospike.client.BatchWrite;
import com.aerospike.client.Host;
import com.aerospike.client.Key;
import com.aerospike.client.Operation;
import com.aerospike.client.Record;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.CommitLevel;
import com.aerospike.client.policy.RecordExistsAction;
import com.aerospike.client.policy.Replica;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public abstract class AerospikeDao implements Closeable {
    private static final ClientPolicy DEFAULT_POLICY = new ClientPolicy();
    private static final String NAMESPACE = "allezon";

    private final String set;
    private final AerospikeClient client;

    static {
        DEFAULT_POLICY.readPolicyDefault.replica = Replica.MASTER_PROLES;
        DEFAULT_POLICY.readPolicyDefault.socketTimeout = 1000;
        DEFAULT_POLICY.readPolicyDefault.totalTimeout = 1000;
        DEFAULT_POLICY.writePolicyDefault.socketTimeout = 15000;
        DEFAULT_POLICY.writePolicyDefault.totalTimeout = 15000;
        DEFAULT_POLICY.writePolicyDefault.maxRetries = 1;
        DEFAULT_POLICY.writePolicyDefault.commitLevel = CommitLevel.COMMIT_MASTER;
        DEFAULT_POLICY.writePolicyDefault.recordExistsAction = RecordExistsAction.UPDATE;
    }

    public AerospikeDao(String set) {
        this.set = set;
        this.client = createClient();
    }

    @Override
    public void close() {
        client.close();
    }

    protected Record getRecord(String key) {
        return client.get(null, createKey(key));
    }

    protected List<Record> getRecords(List<String> keys) {
        return Arrays.asList(client.get(null, createKeys(keys)));
    }

    protected void operate(String key, Operation... operations) {
        client.operate(null, createKey(key), operations);
    }

    protected void operate(List<BatchRecord> batchRecords) {
        client.operate(null, batchRecords);
    }

    protected BatchRecord createBatchWrite(String key, Operation... operations) {
        return new BatchWrite(createKey(key), operations);
    }

    private Key createKey(String key) {
        return new Key(NAMESPACE, set, key);
    }

    private Key[] createKeys(List<String> keys) {
        return keys.stream().map(this::createKey).toArray(Key[]::new);
    }

    private AerospikeClient createClient() {
        try {
            Resource resource = new ClassPathResource("aerospike.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);

            int port = Integer.parseInt(props.getProperty("aerospike.port"));
            String[] seeds = props.getProperty("aerospike.seeds").split(",");

            return new AerospikeClient(DEFAULT_POLICY, Arrays.stream(seeds)
                    .map(seed -> new Host(seed, port)).toArray(Host[]::new));
        } catch (IOException exception) {
            throw new RuntimeException("Failed to locate aerospike.properties");
        }
    }
}
