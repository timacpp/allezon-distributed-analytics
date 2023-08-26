package com.allezon.tags;

import com.allezon.domain.UserTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserTagsEventsPublisher {
	private static final Logger logger = LoggerFactory.getLogger(UserTagsEventsPublisher.class);

	@Value("${kafka.user-tags.topic}")
	private String topic;

	@Autowired
	private KafkaTemplate<String, UserTag> kafkaTemplate;

	public void publish(UserTag userTag) {
		logger.info("Publishing user tag: " + userTag);
		kafkaTemplate.send(topic, userTag);
	}
}
