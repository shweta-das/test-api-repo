package com.synchrony.rest.api.publisher;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synchrony.rest.api.model.UserImg;

public class MyKafkaPublisher implements Serializer<UserImg> {

	@Value("${spring.kafka.admin}")
	private static String adminTopic;

	@Value("${spring.kafka.topic}")
	private static String userTopic;

	private final Logger logger = LoggerFactory.getLogger(MyKafkaPublisher.class);

	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	public static KafkaTemplate<String, UserImg> kafkaTemplate;

	@Autowired
	public static KafkaTemplate<String, String> adminTemplate;

	@Override
	public byte[] serialize(String topic, UserImg dto) {
		byte[] retVal = null;
		try {
			retVal = objectMapper.writeValueAsString(dto).getBytes();
		} catch (Exception exception) {
			System.out.println("Error in serializing object" + dto);
		}
		return retVal;
	}

	public void publishMessages(UserImg usrImgDetail) {
		try {
			ProducerRecord<String, UserImg> producerRecord = new ProducerRecord<>(userTopic, usrImgDetail);

			kafkaTemplate.send(producerRecord);

			logger.info("Message " + usrImgDetail.toString() + " sent in topic " + userTopic.toUpperCase() + ".");
		} catch (Exception ex) {
			throw new RuntimeException("Got exception in publishMessages().");
		}

	}

}
