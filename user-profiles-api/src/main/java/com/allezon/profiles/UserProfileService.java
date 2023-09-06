package com.allezon.profiles;

import com.allezon.core.domain.TimeRange;
import com.allezon.core.domain.UserProfile;
import com.allezon.core.domain.UserTag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileService {
	@Autowired
	private UserProfileDao userProfileDao;

	public UserProfile getByCookie(String cookie, TimeRange timeRange, int limit) {
		return userProfileDao.get(cookie).withFilteredTags(timeRange, limit);
	}

	public void appendTag(String cookie, UserTag userTag) {
		userProfileDao.appendTag(cookie, userTag);
	}
}
