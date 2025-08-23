package com.meby98.framework.kafka.mappers;

import com.mercadona.supplychain.lib.common.kafka.domain.KafkaConsumerMessage;
import com.mercadona.supplychain.lib.common.kafka.model.KafkaConsumerMessageMO;

public interface KafkaConsumerMessageMapper<K, V> {

    KafkaConsumerMessageMO toModel(KafkaConsumerMessage<K, V> kafkaConsumerMessage);

    KafkaConsumerMessage<K, V> toDomain(KafkaConsumerMessageMO kafkaConsumerMessageMO);
}
