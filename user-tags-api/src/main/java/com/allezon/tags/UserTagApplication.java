package com.allezon.tags;

import com.allezon.core.dao.UserProfileDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
@Import(UserProfileDao.class)
public class UserTagApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserTagApplication.class, args);
	}
}
