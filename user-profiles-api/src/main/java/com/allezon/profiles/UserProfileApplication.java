package com.allezon.profiles;

import com.allezon.core.dao.UserTagsDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
@Import(UserTagsDao.class)
public class UserProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProfileApplication.class, args);
	}
}
