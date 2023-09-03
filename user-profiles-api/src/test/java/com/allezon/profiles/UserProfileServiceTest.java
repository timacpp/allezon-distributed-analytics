package com.allezon.profiles;

import com.allezon.core.domain.TimeRange;
import com.allezon.core.domain.UserProfile;
import com.allezon.core.domain.UserTag;
import com.allezon.core.dao.UserTagsDao;
import com.allezon.core.dao.response.UserTags;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserProfileServiceTest {

	private static final String COOKIE = "cookie123";

	@MockBean
	private UserTagsDao userTagsDao;

	@Autowired
	private UserProfileService userProfileService;

	@Test
	void shouldGetProfileByCookie() {
		when(userTagsDao.get(COOKIE)).thenReturn(UserTags.EMPTY);
		assertThat(userProfileService.getByCookie(COOKIE, TimeRange.ANY, 200))
				.extracting("cookie")
				.isEqualTo(COOKIE);
	}

	@Test
	void shouldFilterTagsByTime() {
		List<UserTag> tags = List.of(buildUserTag(Instant.now()), buildUserTag(Instant.now().plusSeconds(60)));
		when(userTagsDao.get(anyString())).thenReturn(new UserTags(tags, tags));

		TimeRange timeRange = new TimeRange(Instant.now().minusSeconds(60), Instant.now());
		UserProfile actual = userProfileService.getByCookie(COOKIE, timeRange, 2);

		assertThat(actual.views()).hasSize(1);
		assertThat(actual.buys()).hasSize(1);
	}

	@Test
	void shouldLimitTags() {
		List<UserTag> tags = List.of(buildUserTag(Instant.now()), buildUserTag(Instant.now()));
		when(userTagsDao.get(anyString())).thenReturn(new UserTags(tags, tags));

		UserProfile actual = userProfileService.getByCookie(COOKIE, TimeRange.ANY, 1);

		assertThat(actual.views()).hasSize(1);
		assertThat(actual.buys()).hasSize(1);
	}

	private UserTag buildUserTag(Instant time) {
		return new UserTag(time.toString(), "cookie123", "PL", UserTag.Device.PC, UserTag.Action.VIEW, "origin", null);
	}
}
