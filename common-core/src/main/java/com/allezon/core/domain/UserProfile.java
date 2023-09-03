package com.allezon.core.domain;

import com.allezon.core.dao.response.UserTags;

import java.time.Instant;
import java.util.List;

public record UserProfile(String cookie, List<UserTag> views, List<UserTag> buys) {

	public UserProfile(String cookie, UserTags tags) {
		this(cookie, tags.views(), tags.buys());
	}

	public UserProfile withFilteredTags(TimeRange timeRange, int limit) {
		return new UserProfile(cookie, filterTags(views, timeRange, limit), filterTags(buys, timeRange, limit));
	}

	private static List<UserTag> filterTags(List<UserTag> tags, TimeRange timeRange, int limit) {
		return tags.stream()
				.filter(tag -> timeRange.includes(Instant.parse(tag.time())))
				.limit(limit)
				.toList();
	}
}
