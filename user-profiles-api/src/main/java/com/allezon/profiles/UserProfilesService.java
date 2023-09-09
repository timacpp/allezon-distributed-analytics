package com.allezon.profiles;

import com.allezon.core.dao.UserProfilesDao;
import com.allezon.core.domain.common.TimeRange;
import com.allezon.core.domain.profile.UserProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfilesService {
	@Autowired
	private UserProfilesDao userProfilesDao;

	public UserProfile getByCookie(String cookie, TimeRange timeRange, int limit) {
		return userProfilesDao.get(cookie).withFilteredTags(timeRange, limit);
	}
}
