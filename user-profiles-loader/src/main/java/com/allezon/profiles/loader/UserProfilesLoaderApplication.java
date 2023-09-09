package com.allezon.profiles.loader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

import com.allezon.core.dao.UserProfilesDao;

@EnableKafka
@SpringBootApplication
@Import(UserProfilesDao.class)
public class UserProfilesLoaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProfilesLoaderApplication.class, args);
	}
}
