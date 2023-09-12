package com.allezon.profiles;

import com.allezon.core.dao.UserProfilesDao;
import com.allezon.core.domain.common.TimeRange;
import com.allezon.core.domain.profile.UserProfile;
import com.allezon.core.domain.tag.Action;
import com.allezon.core.domain.tag.Device;
import com.allezon.core.domain.tag.UserTag;

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
public class UserProfilesServiceTest {

	private static final String COOKIE = "cookie123";

	@MockBean
	private UserProfilesDao userProfilesDao;

	@Autowired
	private UserProfilesService userProfilesService;

	@Test
	void shouldGetProfileByCookie() {
		when(userProfilesDao.get(COOKIE)).thenReturn(new UserProfile(COOKIE, List.of(), List.of()));
		assertThat(userProfilesService.getByCookie(COOKIE, TimeRange.ANY, 200))
				.extracting("cookie")
				.isEqualTo(COOKIE);
	}

	@Test
	void shouldFilterTagsByTime() {
		List<UserTag> tags = List.of(buildUserTag(Instant.now()), buildUserTag(Instant.now().plusSeconds(60)));
		when(userProfilesDao.get(anyString())).thenReturn(new UserProfile(COOKIE, tags, tags));

		TimeRange timeRange = new TimeRange(Instant.now().minusSeconds(60), Instant.now());
		UserProfile actual = userProfilesService.getByCookie(COOKIE, timeRange, 2);

		assertThat(actual.views()).hasSize(1);
		assertThat(actual.buys()).hasSize(1);
	}

	@Test
	void shouldLimitTags() {
		List<UserTag> tags = List.of(buildUserTag(Instant.now()), buildUserTag(Instant.now()));
		when(userProfilesDao.get(anyString())).thenReturn(new UserProfile(COOKIE, tags, tags));

		UserProfile actual = userProfilesService.getByCookie(COOKIE, TimeRange.ANY, 1);

		assertThat(actual.views()).hasSize(1);
		assertThat(actual.buys()).hasSize(1);
	}

	private UserTag buildUserTag(Instant time) {
		return new UserTag(time.toString(), "cookie123", "PL", Device.PC, Action.VIEW, "origin", null);
	}
}
