package com.allezon.tags;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@SpringBootApplication
public class UserTagsApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserTagsApplication.class, args);
	}

	@Bean
	public NewTopic newTopic() {
		return TopicBuilder.name("user-tags")
				.partitions(2)
				.replicas(2)
				.config(TopicConfig.RETENTION_MS_CONFIG, String.valueOf((10 * 60 * 60 * 1000)))
				.config(TopicConfig.CLEANUP_POLICY_CONFIG, "delete")
				.build();
	}
}
