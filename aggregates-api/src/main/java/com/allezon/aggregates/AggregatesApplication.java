package com.allezon.aggregates;

import com.allezon.core.dao.UserTagsDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class AggregatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AggregatesApplication.class, args);
	}

}
