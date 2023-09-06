package com.allezon.profiles;

import com.aerospike.client.Record;
import com.aerospike.client.cdt.ListOperation;
import com.aerospike.client.cdt.ListReturnType;
import com.allezon.core.dao.AerospikeDao;
import com.allezon.core.domain.UserProfile;
import com.allezon.core.domain.UserTag;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileDao extends AerospikeDao<UserProfile> {
    private static final String SET = "profiles";
    private static final String VIEWS_BIN = "views";
    private static final String BUYS_BIN = "buys";
    private static final int MAX_TAGS_PER_ACTION = 200;

	public UserProfileDao(@Value("${aerospike.seeds}") String[] seeds, @Value("${aerospike.port}") int port) {
		super(SET, seeds, port);
	}

    @Override
    public UserProfile get(String cookie) {
        Record record = getRecord(cookie);
        return new UserProfile(cookie, (List<UserTag>) record.getList(VIEWS_BIN), (List<UserTag>) record.getList(BUYS_BIN));
    }

    public void appendTag(String cookie, UserTag userTag) {
        String bin = userTag.action() == UserTag.Action.BUY ? BUYS_BIN : VIEWS_BIN;
        operate(cookie, ListOperation.append(bin, createValue(userTag)),
                ListOperation.removeByIndexRange(bin, -MAX_TAGS_PER_ACTION, MAX_TAGS_PER_ACTION, ListReturnType.INVERTED));
    }
}
