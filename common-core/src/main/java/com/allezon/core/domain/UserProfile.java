package com.allezon.core.domain;

import java.util.List;

public record UserProfile(String cookie, List<UserTag> views, List<UserTag> buys) {
	public UserProfile withFilteredTags(TimeRange timeRange, int limit) {
		return new UserProfile(cookie, filterTags(views, timeRange, limit), filterTags(buys, timeRange, limit));
	}

	private static List<UserTag> filterTags(List<UserTag> tags, TimeRange timeRange, int limit) {
		return tags.stream()
				.filter(tag -> timeRange.includes(tag.time()))
				.limit(limit)
				.toList();
	}
}
