package com.kafka.consumer.listener;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.AppConstants.AppConstants;
import com.kafka.consumer.model.UserImg;

@Configuration
public class MyKafkaConsumer implements Deserializer<UserImg> {

	private final Logger logger = LoggerFactory.getLogger(MyKafkaConsumer.class);

	private static ObjectMapper mapper = new ObjectMapper();

	@KafkaListener(topics = AppConstants.TOPIC_DTO, groupId = AppConstants.GROUP, containerFactory = "kafkaListenerContainerFactory")
	public void consumeMessages(UserImg dto) {
		logger.info("Message: " + dto.toString());
	}

	@Override
	public UserImg deserialize(String topic, byte[] data) {
		UserImg object = null;
		try {
			object = mapper.readValue(data, UserImg.class);
		} catch (Exception exception) {
			System.out.println("Error in deserializing bytes " + exception);
		}
		return object;
	}

}
