package com.allezon.tags;

import com.allezon.core.dao.UserProfilesDao;
import com.allezon.core.domain.tag.UserTag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_tags")
public class UserTagsController {

	@Autowired
	private UserTagsEventPublisher userTagEventsPublisher;

	@Autowired
	private UserProfilesDao userProfilesDao;

	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addUserTag(@RequestBody(required = false) UserTag userTag) {
		if (userTag != null) {
			userProfilesDao.appendTag(userTag);
			userTagEventsPublisher.publish(userTag);
		}
	}
}
