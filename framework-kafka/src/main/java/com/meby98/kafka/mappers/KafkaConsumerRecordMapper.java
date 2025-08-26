package com.meby98.kafka.mappers;

import com.meby98.kafka.domain.KafkaConsumerErrorMessage;
import com.meby98.kafka.domain.KafkaConsumerMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaConsumerRecordMapper<K, V> {
    KafkaConsumerMessage<K, V> toDomain(ConsumerRecord<K, V> consumerRecord);
    KafkaConsumerErrorMessage<K, V> toError(ConsumerRecord<K, V> consumerRecord, String exception);
    KafkaConsumerErrorMessage<K, V> toError(KafkaConsumerMessage<K, V> message, String exception);
}
