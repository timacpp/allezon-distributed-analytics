package com.allezon.tags;

import com.allezon.core.domain.UserTag;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@EnableKafka
@SpringBootApplication
public class UserTagApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserTagApplication.class, args);
	}

	@Bean
	public ProducerFactory<String, UserTag> producerFactory(@Value(value = "${kafka.bootstrap-server}") String bootstrapAddress) {
		return new DefaultKafkaProducerFactory<>(Map.of(
				ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress,
				ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
				ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
		));
	}

	@Bean
	public KafkaTemplate<String, UserTag> kafkaTemplate(@Value(value = "${kafka.bootstrap-server}") String bootstrapAddress) {
		return new KafkaTemplate<>(producerFactory(bootstrapAddress));
	}
}
