package com.meby98.kafka.mappers;

import com.meby98.kafka.domain.KafkaConsumerMessage;
import com.meby98.kafka.model.KafkaConsumerMessageMO;

public interface KafkaConsumerMessageMapper<K, V> {

    KafkaConsumerMessageMO toModel(KafkaConsumerMessage<K, V> kafkaConsumerMessage);

    KafkaConsumerMessage<K, V> toDomain(KafkaConsumerMessageMO kafkaConsumerMessageMO);
}
