package com.allezon.profiles;

import com.allezon.core.domain.UserTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserTagEventConsumer {
	private static final Logger logger = LoggerFactory.getLogger(UserTagEventConsumer.class);

	@Autowired
	private UserProfileDao userProfileDao;

	@KafkaListener(topics = "user-tags")
	void consume(UserTag userTag) {
		logger.debug("Consuming user tag: {}", userTag);
		userProfileDao.appendTag(userTag.cookie(), userTag);
	}
}
