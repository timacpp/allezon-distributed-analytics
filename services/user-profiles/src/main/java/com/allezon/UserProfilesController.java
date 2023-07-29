package com.allezon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_profiles")
public class UserProfilesController {

	@Autowired
	private UserProfilesService userProfilesService;

	@PostMapping(value = "/{cookie}")
	public ResponseEntity<Void> getUserProfiles(
			@PathVariable(name = "cookie") String cookie,
			@RequestParam(name = "time_range") String timeRange,
			@RequestParam(name = "limit", required = false, defaultValue = "200") int limit) {
		return ResponseEntity.ok().build();
	}
}
