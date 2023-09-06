package com.allezon.profiles;

import com.allezon.core.domain.TimeRange;
import com.allezon.core.domain.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_profiles")
public class UserProfileController {

	private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

	@Autowired
	private UserProfileService userProfileService;

	@PostMapping(value = "/{cookie}")
	public ResponseEntity<UserProfile> getUserProfile(
			@PathVariable(name = "cookie") String cookie,
			@RequestParam(name = "time_range") String timeRange,
			@RequestParam(name = "limit", required = false, defaultValue = "200") int limit,
			@RequestBody(required = false) UserProfile expectedUserProfile) {
        logger.debug("Getting profile for cookie={} with timeRange={}, limit={}", cookie, timeRange, limit);
		UserProfile userProfile = userProfileService.getByCookie(cookie, TimeRange.parse(timeRange), limit);

		if (!userProfile.equals(expectedUserProfile)) {
			logger.error("User profile differs: actual={}, expected={}", userProfile, expectedUserProfile);
		}

		return ResponseEntity.ok(userProfile);
	}
}
