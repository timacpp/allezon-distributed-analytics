package com.allezon.profiles.loader;

import com.allezon.core.dao.UserProfilesDao;
import com.allezon.core.domain.tag.UserTag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserProfilesLoaderApplicationTest {

	@MockBean
	private UserProfilesDao userProfilesDao;

	@Autowired
	private UserProfilesTagEventConsumer userProfilesTagEventConsumer;

	@Test
	void shouldAppendTagToUserProfileAfterConsumingEvent() {
		UserTag userTag = buildUserTag();
		userProfilesTagEventConsumer.consume(userTag);
		verify(userProfilesDao).appendTag(userTag);
	}

	private UserTag buildUserTag() {
		return new UserTag(Instant.now().toString(),"cookie123", "PL", UserTag.Device.PC, UserTag.Action.VIEW, "origin", null);
	}
}
