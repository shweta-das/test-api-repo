package com.kafka.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafka.consumer.model.FailedMessage;

public interface FailedMessageRepository extends JpaRepository<FailedMessage, Long>{

}
