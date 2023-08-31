package com.allezon.profiles;

import com.allezon.core.domain.UserTag;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@SpringBootApplication
public class UserProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProfileApplication.class, args);
	}

	@Bean
	public ConsumerFactory<String, UserTag> consumerFactory(@Value("${kafka.bootstrap-server}") String bootstrapServer) {
		return new DefaultKafkaConsumerFactory<>(Map.of(
				ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer,
				ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
				ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class
		));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserTag> kafkaListenerContainerFactory(@Value("${kafka.bootstrap-server}") String bootstrapServer) {
		ConcurrentKafkaListenerContainerFactory<String, UserTag> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory(bootstrapServer));
		return factory;
	}
}
