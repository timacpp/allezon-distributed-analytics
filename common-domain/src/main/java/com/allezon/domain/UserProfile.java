package com.allezon.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public record UserProfile(String cookie, LinkedList<UserTag> views, LinkedList<UserTag> buys) {
	private static final int MAX_TAGS_PER_ACTION = 200;

	public UserProfile withFilteredTags(TimeRange timeRange, int limit) {
		return new UserProfile(cookie, filterTags(views, timeRange, limit), filterTags(buys, timeRange, limit));
	}

	public UserProfile withAdditionalTag(UserTag userTag) {
		if (userTag.action() == UserTag.Action.BUY) {
			if (views.size() == MAX_TAGS_PER_ACTION) {
				views.removeLast();
			}
			views.addFirst(userTag);
		} else {
			if (buys.size() == MAX_TAGS_PER_ACTION) {
				buys.removeLast();
			}
			buys.addFirst(userTag);
		}

		return this;
	}

	private static LinkedList<UserTag> filterTags(List<UserTag> tags, TimeRange timeRange, int limit) {
		return tags.stream()
				.filter(tag -> timeRange.includes(tag.time()))
				.limit(limit)
				.collect(Collectors.toCollection(LinkedList::new));
	}
}
