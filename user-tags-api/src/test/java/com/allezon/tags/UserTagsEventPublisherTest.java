package com.allezon.tags;

import com.allezon.core.domain.tag.UserTag;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserTagsEventPublisherTest {

	@MockBean
	private KafkaTemplate<String, UserTag> kafkaTemplate;

	@Autowired
	private UserTagsEventPublisher userTagsEventPublisher;

	@Test
	void shouldSendEventsToUserTagsTopic() {
		UserTag userTag = buildUserTag();
		userTagsEventPublisher.publish(userTag);
		verify(kafkaTemplate).send("user-tags", userTag);
	}

	private UserTag buildUserTag() {
		return new UserTag(Instant.now().toString(), "cookie123", "PL", UserTag.Device.PC, UserTag.Action.VIEW, "origin", null);
	}
}
