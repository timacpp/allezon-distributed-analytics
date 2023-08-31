package com.allezon.profiles;

import com.allezon.core.domain.UserTag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserTagEventConsumerTest {
	private static final String COOKIE = "cookie123";

	@MockBean
	private UserProfileRepository userProfileRepository;

	@MockBean
	private UserProfileService userProfileService;

	@Autowired
	private UserTagEventConsumer userTagEventConsumer;

	@Test
	void shouldUpdateUserProfileAfterConsumingEvent() {
		UserTag userTag = buildUserTag();
		userTagEventConsumer.consume(userTag);
		verify(userProfileService).updateByCookie(COOKIE, userTag);
	}

	private UserTag buildUserTag() {
		return new UserTag(Instant.now().toString(), COOKIE, "PL", UserTag.Device.PC, UserTag.Action.VIEW, "origin", null);
	}
}
