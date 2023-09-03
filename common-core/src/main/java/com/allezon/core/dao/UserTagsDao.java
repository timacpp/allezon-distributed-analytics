package com.allezon.core.dao;

import com.aerospike.client.Record;
import com.aerospike.client.cdt.ListOperation;
import com.aerospike.client.cdt.ListReturnType;
import com.allezon.core.domain.UserTag;
import com.allezon.core.dao.response.UserTags;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserTagsDao extends AerospikeDao<UserTag> {
	private static final String SET = "tags";
	private static final String VIEWS_BIN = "views";
	private static final String BUYS_BIN = "buys";
	private static final int MAX_TAGS_PER_ACTION = 200;

	public UserTagsDao(@Value("${aerospike.seeds}") String[] seeds, @Value("${aerospike.port}") int port) {
		super(SET, seeds, port);
	}

	@Override
	public UserTags get(String cookie) {
		Record record = getRecord(cookie);
		return new UserTags((List<UserTag>) record.getList(VIEWS_BIN), (List<UserTag>) record.getList(BUYS_BIN));
	}

	@Override
	public void save(UserTag userTag) {
		String bin = userTag.action() == UserTag.Action.BUY ? BUYS_BIN : VIEWS_BIN;
		operate(userTag.cookie(), ListOperation.append(bin, createValue(userTag)),
				ListOperation.removeByIndexRange(bin, -MAX_TAGS_PER_ACTION, MAX_TAGS_PER_ACTION, ListReturnType.INVERTED));
	}

	@Override
	@PreDestroy
	public void close() {
		closeClient();
	}
}
