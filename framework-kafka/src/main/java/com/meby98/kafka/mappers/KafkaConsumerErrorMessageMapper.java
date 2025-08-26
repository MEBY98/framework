package com.meby98.kafka.mappers;

import com.meby98.kafka.domain.KafkaConsumerErrorMessage;
import com.meby98.kafka.model.KafkaConsumerErrorMessageMO;

public interface KafkaConsumerErrorMessageMapper<K, V> {
  KafkaConsumerErrorMessageMO toModel(KafkaConsumerErrorMessage<K, V> kafkaConsumerErrorMessage);

  KafkaConsumerErrorMessage<K, V> toDomain(KafkaConsumerErrorMessageMO kafkaConsumerErrorMessageMO);
}
