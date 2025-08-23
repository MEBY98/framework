package com.meby98.framework.kafka;

import com.mercadona.supplychain.lib.common.kafka.domain.KafkaConsumerMessage;

import java.util.List;

public interface SaveKafkaConsumerMessageRepositoryPort<K, V> {
    void save(KafkaConsumerMessage<K, V> message);
    void saveAll(List<KafkaConsumerMessage<K, V>> messages);
}
