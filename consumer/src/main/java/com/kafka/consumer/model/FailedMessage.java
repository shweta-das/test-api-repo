package com.kafka.consumer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "failmsgs")
public class FailedMessage {

	@Id
	@Column(name = "id", length = 10)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "message")
	private String message;

	@Column(name = "topic")
	private String topic;
	
	@Column(name = "exception")
	private String exception;
	
	public FailedMessage(String message) { this.message = message; }
	
	public FailedMessage(String message, String topic, String exception) { 
		this.message = message; 
		this.topic = topic;
		this.exception = exception;
	}

	public FailedMessage() {
	}
}
