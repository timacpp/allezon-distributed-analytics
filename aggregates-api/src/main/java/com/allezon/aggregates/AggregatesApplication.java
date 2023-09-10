package com.allezon.aggregates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.allezon.core.dao.AggregatesDao;

@SpringBootApplication
@Import(AggregatesDao.class)
public class AggregatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AggregatesApplication.class, args);
	}

}
