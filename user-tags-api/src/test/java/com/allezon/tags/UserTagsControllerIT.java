package com.allezon.tags;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.allezon.core.dao.UserProfilesDao;
import com.allezon.core.domain.tag.UserTag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserTagsController.class)
public class UserTagsControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserTagsEventPublisher userTagEventsPublisher;


	@MockBean
	private UserProfilesDao userProfilesDao;

	@Test
	void shouldPublishEventWhenAddingUserTag() throws Exception {
		String userTagJson = """
		{
			"time": "2022-03-22T12:15:00.000Z",
			"cookie": "cookie123",
			"country": "PL",
			"device": "PC",
			"action": "BUY",
			"origin": "www.apple.com",
			"product_info": {
				"product_id": "iPhone14",
				"brand_id": "Apple",
				"category_id": "phone",
				"price": 4000
			}
		}""";

		mockMvc.perform(post("/user_tags")
				.content(userTagJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

		UserTag tag = userTagFromJson(userTagJson);
		verify(userTagEventsPublisher).publish(eq(tag));
		verify(userProfilesDao).appendTag(eq(tag));
	}

	private UserTag userTagFromJson(String json) throws JsonProcessingException {
		return new ObjectMapper().readValue(json, UserTag.class);
	}
}
