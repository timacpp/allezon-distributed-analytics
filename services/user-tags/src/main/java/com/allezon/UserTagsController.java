package com.allezon;

import com.allezon.domain.UserTag;
import com.allezon.UserTagsEventsPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_tags")
public class UserTagsController {

	@Autowired
	private UserTagsEventsPublisher userTagsEventsPublisher;

	@PostMapping
	public ResponseEntity<Void> addUserTag(@RequestBody UserTag userTag) {
		userTagsEventsPublisher.publish(userTag);
		return ResponseEntity.noContent().build();
	}
}
