package com.allezon.core.dao;

import com.aerospike.client.*;
import com.aerospike.client.Record;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.CommitLevel;
import com.aerospike.client.policy.RecordExistsAction;
import com.aerospike.client.policy.Replica;
import com.allezon.core.dao.response.DaoResponse;

import java.io.Closeable;
import java.util.Arrays;

public abstract class AerospikeDao<T> implements Closeable {
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
		DEFAULT_POLICY.writePolicyDefault.recordExistsAction = RecordExistsAction.REPLACE;
	}

	public AerospikeDao(String set, String[] seeds, int port) {
		this.set = set;
		this.client = new AerospikeClient(DEFAULT_POLICY,
				Arrays.stream(seeds).map(seed -> new Host(seed, port)).toArray(Host[]::new));
	}

	public abstract DaoResponse<T> get(String key);

	public abstract void save(T value);

	protected void closeClient() {
		client.close();
	}

	protected Record getRecord(String key) {
		return client.get(null, createKey(key));
	}

	protected void operate(String key, Operation... operations) {
		client.operate(null, createKey(key), operations);
	}

	protected Value createValue(Object value) {
		return Value.get(value);
	}

	private Key createKey(String key) {
		return new Key(NAMESPACE, set, key);
	}
}
