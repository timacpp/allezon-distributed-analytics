package com.allezon;


import com.allezon.kafka.KafkaEventsPublisher;
import org.springframework.beans.factory.annotation.Value;

import com.allezon.domain.UserTag;

public class UserTagsEventsPublisher extends KafkaEventsPublisher<UserTag> {

	public UserTagsEventsPublisher(@Value("${user-tags.topic.name}") String topic) {
		super(topic);
	}
}
