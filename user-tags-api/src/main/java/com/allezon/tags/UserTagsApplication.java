package com.allezon.tags;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Map;

@EnableKafka
@SpringBootApplication
public class UserTagsApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserTagsApplication.class, args);
	}

	@Bean
	public Map<String, Object> producerConfig(@Value(value = "${kafka.bootstrap-server}") String kafkaBootstrapAddress) {
		return Map.of(
				ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapAddress,
				ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
				ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
		);
	}
}
