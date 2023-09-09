package com.allezon.profiles.loader;

import com.allezon.core.domain.common.TimeRange;
import com.allezon.core.domain.profile.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_profiles")
public class UserProfilesController {

	private static final Logger logger = LoggerFactory.getLogger(UserProfilesController.class);

	@Autowired
	private UserProfilesService userProfilesService;

	@PostMapping(value = "/{cookie}")
	public ResponseEntity<UserProfile> getUserProfile(
			@PathVariable(name = "cookie") String cookie,
			@RequestParam(name = "time_range") String timeRange,
			@RequestParam(name = "limit", required = false, defaultValue = "200") int limit,
			@RequestBody(required = false) UserProfile expectedUserProfile) {
		UserProfile userProfile = userProfilesService.getByCookie(cookie, TimeRange.parse(timeRange), limit);

		if (!userProfile.equals(expectedUserProfile)) {
			logger.error("User profile differs for timeRange={}, limit={}, actual={}, expected={}",
					timeRange, limit, userProfile, expectedUserProfile);
		}

		return ResponseEntity.ok(userProfile);
	}
}
