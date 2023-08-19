package com.allezon.tags;

import com.allezon.tags.domain.UserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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