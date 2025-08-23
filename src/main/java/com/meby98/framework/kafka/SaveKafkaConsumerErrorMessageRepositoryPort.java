package com.meby98.framework.kafka;

import com.mercadona.supplychain.lib.common.kafka.domain.KafkaConsumerErrorMessage;

import java.util.List;

public interface SaveKafkaConsumerErrorMessageRepositoryPort<K, V> {
  void save(KafkaConsumerErrorMessage<K, V> message);
  void saveAll(List<KafkaConsumerErrorMessage<K, V>> messageList);
}
