package com.allezon.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public abstract class KafkaEventsPublisher<T> {
	private final String topic;

	public KafkaEventsPublisher(String topic) {
		this.topic = topic;
	}

	public void publish(T data) {

	}
}
