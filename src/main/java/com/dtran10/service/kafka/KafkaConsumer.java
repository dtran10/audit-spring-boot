package com.dtran10.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.apachecommons.CommonsLog;

@Component
@CommonsLog
public class KafkaConsumer {

		@Autowired
		private KafkaProducer producer;
		
		@KafkaListener(topics="audit-test")
		public void processMessage(String messageContent) {
			log.info("Received message from Kafka: " + messageContent);
			producer.produceResponseMessage("Response to: " + messageContent);
		}
}
