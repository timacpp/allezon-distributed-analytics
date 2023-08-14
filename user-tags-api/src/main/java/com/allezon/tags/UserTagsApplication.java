package com.allezon.tags;

import com.allezon.domain.UserTag;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.schema.registry.client.EnableSchemaRegistryClient;
import org.springframework.cloud.schema.registry.client.SchemaRegistryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Map;

@EnableKafka
@EnableSchemaRegistryClient
@SpringBootApplication
public class UserTagsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(UserTagsApplication.class, args);
		SchemaRegistryClient schemaRegistryClient = context.getBean(SchemaRegistryClient.class);
		schemaRegistryClient.register("user-tags", "avro", UserTag.getClassSchema().toString());
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
