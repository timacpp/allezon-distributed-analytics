package com.allezon.tags;

import com.allezon.core.dao.UserTagsDao;
import com.allezon.core.domain.UserTag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserTagEventConsumerTest {

	@MockBean
	private UserTagsDao userTagsDao;

	@Autowired
	private UserTagEventConsumer userTagEventConsumer;

	@Test
	void shouldUpdateUserProfileAfterConsumingEvent() {
		UserTag userTag = buildUserTag();
		userTagEventConsumer.consume(userTag);
		verify(userTagsDao).save(userTag);
	}

	private UserTag buildUserTag() {
		return new UserTag(Instant.now().toString(),"cookie123", "PL", UserTag.Device.PC, UserTag.Action.VIEW, "origin", null);
	}
}
