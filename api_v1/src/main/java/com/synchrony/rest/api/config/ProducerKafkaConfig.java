package com.synchrony.rest.api.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.synchrony.rest.api.model.UserImg;

//@Configuration
public class ProducerKafkaConfig {

	@Value("${spring.kafka.producer.bootstrap-servers}")
	private String bootstrapAddress;

	@Value("${spring.kafka.adminTopic}")
	private static String adminTopic;
	
	@Value("${spring.kafka.dtoTopic}")
	private static String dtoTopic;

	@Bean
	public NewTopic newTopic() {
		return TopicBuilder.name(dtoTopic).build();
	}

	@Bean
	public ProducerFactory<String, String> kafkaAdminProducer() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory(configProps);
	}				

	@Bean
	public KafkaTemplate<String, String> kafkaTemplateAdmin() {
		return new KafkaTemplate<>(kafkaAdminProducer());
	}

	@Bean
	public ProducerFactory<String, UserImg> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, UserImg> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	
}
