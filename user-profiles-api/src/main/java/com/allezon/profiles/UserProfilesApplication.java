package com.allezon.profiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.allezon.core.dao.UserProfilesDao;

@SpringBootApplication
@Import(UserProfilesDao.class)
public class UserProfilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProfilesApplication.class, args);
	}
}
