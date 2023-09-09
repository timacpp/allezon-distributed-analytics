package com.allezon.profiles.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import com.allezon.core.dao.UserProfilesDao;
import com.allezon.core.domain.tag.UserTag;

@EnableKafka
@SpringBootApplication
@Import(UserProfilesDao.class)
public class UserProfilesLoader {
	private static final Logger logger = LoggerFactory.getLogger(UserProfilesLoader.class);

	@Autowired
	private UserProfilesDao userProfilesDao;

	public static void main(String[] args) {
		SpringApplication.run(UserProfilesLoader.class, args);
	}

	@KafkaListener(topics = "user-tags")
	public void loadTag(UserTag userTag) {
		logger.info("Consuming user tag={}", userTag);
		userProfilesDao.appendTag(userTag);
	}
}
