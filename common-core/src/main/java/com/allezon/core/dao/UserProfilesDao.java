package com.allezon.core.dao;

import com.aerospike.client.Record;
import com.aerospike.client.Value;
import com.aerospike.client.cdt.ListOperation;
import com.aerospike.client.cdt.ListReturnType;
import com.allezon.core.domain.profile.UserProfile;
import com.allezon.core.domain.tag.UserTag;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfilesDao extends AerospikeDao {
    private static final String SET = "profiles";
    private static final String VIEWS_BIN = "views";
    private static final String BUYS_BIN = "buys";
    private static final int MAX_TAGS_PER_ACTION = 200;

	public UserProfilesDao() {
		super(SET);
	}

    public UserProfile get(String cookie) {
        Record record = getRecord(cookie);
        List<UserTag> views = record != null ? (List<UserTag>) record.getList(VIEWS_BIN) : List.of();
        List<UserTag> buys = record != null ? (List<UserTag>) record.getList(BUYS_BIN) : List.of();
        return new UserProfile(cookie, views, buys);
    }

    public void appendTag(UserTag userTag) {
        String bin = userTag.action() == UserTag.Action.BUY ? BUYS_BIN : VIEWS_BIN;
        operate(userTag.cookie(),
                ListOperation.append(bin, Value.get(userTag)),
                ListOperation.removeByIndexRange(bin, -MAX_TAGS_PER_ACTION, MAX_TAGS_PER_ACTION, ListReturnType.INVERTED));
    }
}
