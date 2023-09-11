package com.allezon.core.domain.profile;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

import com.allezon.core.domain.common.TimeRange;
import com.allezon.core.domain.tag.UserTag;

public record UserProfile(String cookie, List<UserTag> views, List<UserTag> buys) {
	public UserProfile withFilteredTags(TimeRange timeRange, int limit) {
		return new UserProfile(cookie, filterTags(views, timeRange, limit), filterTags(buys, timeRange, limit));
	}

	private static List<UserTag> filterTags(List<UserTag> tags, TimeRange timeRange, int limit) {
		if (tags == null) {
			return List.of();
		}
		return tags.stream()
				.filter(tag -> timeRange.includes(Instant.parse(tag.time())))
				.sorted(Comparator.comparing(UserTag::time).reversed())
				.limit(limit)
				.toList();
	}
}
