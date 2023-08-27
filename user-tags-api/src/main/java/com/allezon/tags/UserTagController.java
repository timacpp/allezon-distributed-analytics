package com.allezon.tags;

import com.allezon.core.domain.UserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_tags")
public class UserTagController {

	@Autowired
	private UserTagEventPublisher userTagEventsPublisher;

	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addUserTag(@RequestBody UserTag userTag) {
		userTagEventsPublisher.publish(userTag);
	}
}
