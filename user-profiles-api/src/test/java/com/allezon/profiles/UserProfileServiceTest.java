package com.allezon.profiles;

import com.allezon.core.domain.TimeRange;
import com.allezon.core.domain.UserProfile;
import com.allezon.core.domain.UserTag;
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
	private UserProfileRepository userProfileRepository;

	@Autowired
	private UserProfileService userProfileService;

	@Test
	void shouldGetProfileByCookie() {
		UserProfile userProfile = new UserProfile(COOKIE, List.of(), List.of());
		when(userProfileRepository.get(COOKIE)).thenReturn(userProfile);
		assertThat(userProfileService.getByCookie(COOKIE, TimeRange.ANY, 200))
				.extracting("cookie")
				.isEqualTo(COOKIE);
	}

	@Test
	void shouldFilterTagsByTime() {
		List<UserTag> userTags = List.of(buildUserTag(Instant.now()), buildUserTag(Instant.now().plusSeconds(60)));
		UserProfile userProfile = new UserProfile(COOKIE, userTags, userTags);
		when(userProfileRepository.get(anyString())).thenReturn(userProfile);

		TimeRange timeRange = new TimeRange(Instant.now().minusSeconds(60), Instant.now());
		UserProfile actual = userProfileService.getByCookie(COOKIE, timeRange, 2);

		assertThat(actual.views()).hasSize(1);
		assertThat(actual.buys()).hasSize(1);
	}

	@Test
	void shouldLimitTags() {
		List<UserTag> userTags = List.of(buildUserTag(Instant.now()), buildUserTag(Instant.now()));
		UserProfile userProfile = new UserProfile(COOKIE, userTags, userTags);
		when(userProfileRepository.get(anyString())).thenReturn(userProfile);

		UserProfile actual = userProfileService.getByCookie(COOKIE, TimeRange.ANY, 1);

		assertThat(actual.views()).hasSize(1);
		assertThat(actual.buys()).hasSize(1);
	}

	private UserTag buildUserTag(Instant time) {
		return new UserTag(time.toString(), "cookie123", "PL", UserTag.Device.PC, UserTag.Action.VIEW, "origin", null);
	}
}
