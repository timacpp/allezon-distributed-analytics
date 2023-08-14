package com.allezon.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.allezon.domain.UserTag;

@RestController
@RequestMapping("/user_tags")
public class UserTagsController {

	@Autowired
	private UserTagsEventsPublisher userTagsEventsPublisher;

	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addUserTag(@RequestBody UserTag userTag) {
		userTagsEventsPublisher.publish(userTag);
	}
}
