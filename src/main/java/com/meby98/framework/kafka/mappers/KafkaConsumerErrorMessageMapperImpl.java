package com.meby98.framework.kafka.mappers;

import com.google.gson.Gson;
import com.mercadona.supplychain.lib.common.kafka.domain.KafkaConsumerErrorMessage;
import com.mercadona.supplychain.lib.common.kafka.model.KafkaConsumerErrorMessageMO;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaConsumerErrorMessageMapperImpl<K, V> implements KafkaConsumerErrorMessageMapper<K, V> {
  private static final Gson gson = new Gson();
  @Override
  public KafkaConsumerErrorMessageMO toModel(final KafkaConsumerErrorMessage<K, V> kafkaConsumerErrorMessage) {
    return KafkaConsumerErrorMessageMO.builder()
        .offset(kafkaConsumerErrorMessage.getOffset())
        .topic(kafkaConsumerErrorMessage.getTopic())
        .receivedAt(kafkaConsumerErrorMessage.getReceivedAt())
        .partition(kafkaConsumerErrorMessage.getPartition())
        .key(gson.toJson(kafkaConsumerErrorMessage.getKey()))
        .value(gson.toJson(kafkaConsumerErrorMessage.getValue()))
        .header(gson.toJson(kafkaConsumerErrorMessage.getHeader()))
        .exception(kafkaConsumerErrorMessage.getException())
        .build();
  }

  @SuppressWarnings("unchecked")
  @Override
  public KafkaConsumerErrorMessage<K, V> toDomain(final KafkaConsumerErrorMessageMO kafkaConsumerErrorMessageMO) {
    return (KafkaConsumerErrorMessage<K, V>) KafkaConsumerErrorMessage.<K, V>builder()
        .exception(kafkaConsumerErrorMessageMO.getException())
        .offset(kafkaConsumerErrorMessageMO.getOffset())
        .topic(kafkaConsumerErrorMessageMO.getTopic())
        .receivedAt(kafkaConsumerErrorMessageMO.getReceivedAt())
        .partition(kafkaConsumerErrorMessageMO.getPartition())
        .key((K) gson.fromJson(kafkaConsumerErrorMessageMO.getKey(), Object.class))
        .value((V) gson.fromJson(kafkaConsumerErrorMessageMO.getValue(), Object.class))
        .header(gson.fromJson(kafkaConsumerErrorMessageMO.getHeader(), Map.class))
        .build();
  }
}
