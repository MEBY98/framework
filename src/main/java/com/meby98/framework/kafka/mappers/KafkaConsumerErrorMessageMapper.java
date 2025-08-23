package com.meby98.framework.kafka.mappers;

import com.mercadona.supplychain.lib.common.kafka.domain.KafkaConsumerErrorMessage;
import com.mercadona.supplychain.lib.common.kafka.model.KafkaConsumerErrorMessageMO;

public interface KafkaConsumerErrorMessageMapper<K, V> {
  KafkaConsumerErrorMessageMO toModel(KafkaConsumerErrorMessage<K, V> kafkaConsumerErrorMessage);

  KafkaConsumerErrorMessage<K, V> toDomain(KafkaConsumerErrorMessageMO kafkaConsumerErrorMessageMO);
}
