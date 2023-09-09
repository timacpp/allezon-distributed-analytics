package com.allezon.tags;

import com.allezon.core.domain.tag.UserTag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserTagsEventPublisher {
	private static final Logger logger = LoggerFactory.getLogger(UserTagsEventPublisher.class);

	@Autowired
	private KafkaTemplate<String, UserTag> kafkaTemplate;

	public void publish(UserTag userTag) {
		logger.info("Publishing user tag={}", userTag);
		kafkaTemplate.send("user-tags", userTag);
	}
}
