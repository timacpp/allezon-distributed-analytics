package com.allezon.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user_profiles")
public class UserProfilesController {

	@PostMapping(value = "/{cookie}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> getUserProfiles(
			@PathVariable(name = "cookie") String cookie,
			@RequestParam(name = "time_range") String timeRange,
			@RequestParam(name = "limit", required = false, defaultValue = "200") int limit) {
		return ResponseEntity.ok().build();
	}
}
