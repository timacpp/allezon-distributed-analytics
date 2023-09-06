package com.allezon.tags;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class UserTagApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserTagApplication.class, args);
	}
}
