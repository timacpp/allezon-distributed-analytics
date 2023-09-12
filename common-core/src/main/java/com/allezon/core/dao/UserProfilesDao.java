package com.allezon.core.dao;

import com.aerospike.client.Record;
import com.aerospike.client.Value;
import com.aerospike.client.cdt.ListOperation;
import com.aerospike.client.cdt.ListReturnType;
import com.allezon.core.domain.profile.UserProfile;
import com.allezon.core.domain.tag.Action;
import com.allezon.core.domain.tag.UserTag;
import com.allezon.core.serialization.UserTagCompressor;

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
        List<byte[]> views = record != null ? (List<byte[]>) record.getList(VIEWS_BIN) : List.of();
        List<byte[]> buys = record != null ? (List<byte[]>) record.getList(BUYS_BIN) : List.of();
        return new UserProfile(cookie, UserTagCompressor.uncompress(views), UserTagCompressor.uncompress(buys));
    }

    public void appendTag(UserTag userTag)  {
        String bin = userTag.action() == Action.BUY ? BUYS_BIN : VIEWS_BIN;
        operate(userTag.cookie(),
                ListOperation.append(bin, Value.get(UserTagCompressor.compress(userTag))),
                ListOperation.removeByIndexRange(bin, -MAX_TAGS_PER_ACTION, MAX_TAGS_PER_ACTION, ListReturnType.INVERTED));
    }
}
