package com.allezon.core.dao.response;

import com.allezon.core.domain.UserTag;

import java.util.List;

public record UserTags(List<UserTag> views, List<UserTag> buys) implements DaoResponse<UserTag> {
	public static final UserTags EMPTY = new UserTags(List.of(), List.of());
}
