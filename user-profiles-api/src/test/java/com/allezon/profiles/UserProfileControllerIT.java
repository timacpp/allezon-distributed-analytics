package com.allezon.profiles;

import com.allezon.core.domain.TimeRange;
import com.allezon.core.domain.UserProfile;
import com.allezon.core.domain.UserTag;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(UserProfileController.class)
public class UserProfileControllerIT {

	private static final String COOKIE = "cookie123";

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserProfileService userProfileService;

	@Test
	void shouldGetUserProfile() throws Exception {
		UserProfile userProfile = new UserProfile(COOKIE, List.of(buildTag()), List.of(buildTag()));
		when(userProfileService.getByCookie(eq(COOKIE), any(TimeRange.class), anyInt())).thenReturn(userProfile);
		mockMvc.perform(post("/user_profiles/" + COOKIE)
						.param("time_range", "2022-03-22T12:15:00_2025-03-22T12:30:00")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(MAPPER.writeValueAsString(userProfile), false));
	}

	@Test
	void shouldGetUserProfileWithSpecifiedLimit() throws Exception {
		UserProfile emptyUserProfile = new UserProfile(COOKIE, List.of(), List.of());
		when(userProfileService.getByCookie(eq(COOKIE), any(TimeRange.class), eq(0))).thenReturn(emptyUserProfile);
		mockMvc.perform(post("/user_profiles/" + COOKIE)
						.param("time_range", "2022-03-22T12:15:00_2025-03-22T12:30:00")
						.param("limit", "0")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(MAPPER.writeValueAsString(emptyUserProfile), false));
	}

	private UserTag buildTag() {
		return new UserTag(Instant.now().toString(), COOKIE, "PL", UserTag.Device.PC, UserTag.Action.VIEW, "origin", null);
	}
}