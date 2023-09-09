package com.allezon.profiles.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.allezon.core.dao.UserProfilesDao;
import com.allezon.core.domain.tag.UserTag;


@Component
public class UserProfilesTagEventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(UserProfilesLoaderApplication.class);

    @Autowired
    private UserProfilesDao userProfilesDao;

    @KafkaListener(topics = "user-tags")
    public void consume(UserTag userTag) {
        logger.info("Consuming user tag={}", userTag);
        userProfilesDao.appendTag(userTag);
    }
}
