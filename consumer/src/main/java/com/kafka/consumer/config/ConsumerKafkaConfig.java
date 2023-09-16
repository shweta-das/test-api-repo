package com.kafka.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafka.consumer.AppConstants.AppConstants;
import com.kafka.consumer.model.FailedMessage;
import com.kafka.consumer.model.UserImg;

@Configuration
public class ConsumerKafkaConfig {

	@Value("${spring.kafka.dead_letter_topic}")
	private static String deadLetterTopic;

	private final Logger logger = LoggerFactory.getLogger(ConsumerKafkaConfig.class);

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, AppConstants.GROUP);
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(configProps);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	/**
	public ConcurrentKafkaListenerContainerFactory<Object, Object> listenerFactory() {
		ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();

		try {
			factory.setConsumerFactory(MyKafkaConsumerFactory());
			
			 * DeadLetterPublishingRecoverer deadLetterRecovery = new
			 * DeadLetterPublishingRecoverer(kafkaTemplate, (myRecord, ex) -> {
			 * logger.error("Exception handling to get message"), ex.getMessage(),
			 * deadLetterTopic); return new TopicPartition(deadLetterTopic), -1); });
			
		} catch (Exception e) {
			factory.setCommonErrorHandler(getDefaultErrorHandler());
		}
		return factory;
	} **/

	public ConsumerFactory<String, UserImg> userConsumerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, AppConstants.GROUP);
		configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
				new JsonDeserializer<>(UserImg.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserImg> userKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, UserImg> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(userConsumerFactory());
		return factory;
	}

	public DefaultErrorHandler getDefaultErrorHandler() {
		return new DefaultErrorHandler((myRecord, ex) -> {
			FailedMessage failMessageEntity = new FailedMessage();
			try {
				failMessageEntity.setMessage(myRecord.value().toString());
				failMessageEntity.setException(ex.getMessage());
				failMessageEntity.setTopic(deadLetterTopic);
			} catch (Exception e) {
				e.getMessage();
			}
		});
	}		
}
