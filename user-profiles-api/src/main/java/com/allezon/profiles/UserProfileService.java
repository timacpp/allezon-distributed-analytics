package com.allezon.profiles;

import com.allezon.core.dao.UserTagsDao;
import com.allezon.core.domain.TimeRange;
import com.allezon.core.domain.UserProfile;
import com.allezon.core.dao.response.UserTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileService {

	@Autowired
	private UserTagsDao userTagsDao;

	public UserProfile getByCookie(String cookie, TimeRange timeRange, int limit) {
		UserTags tags = userTagsDao.get(cookie);
		return new UserProfile(cookie, tags).withFilteredTags(timeRange, limit);
	}
}
