package com.allezon.profiles;

import com.allezon.core.domain.TimeRange;
import com.allezon.core.domain.UserProfile;
import com.allezon.core.domain.UserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;

	public UserProfile getByCookie(String cookie, TimeRange timeRange, int limit) {
		return userProfileRepository.get(cookie).withFilteredTags(timeRange, limit);
	}

	public void updateByCookie(String cookie, UserTag userTag) {
		userProfileRepository.update(cookie, userTag);
	}
}
