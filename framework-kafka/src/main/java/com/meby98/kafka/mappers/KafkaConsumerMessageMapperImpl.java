package com.meby98.kafka.mappers;

import com.google.gson.Gson;
import com.meby98.kafka.domain.KafkaConsumerMessage;
import com.meby98.kafka.model.KafkaConsumerMessageMO;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class KafkaConsumerMessageMapperImpl<K, V> implements KafkaConsumerMessageMapper<K, V> {

    private static final Gson gson = new Gson();

    public KafkaConsumerMessageMO toModel(KafkaConsumerMessage<K, V> kafkaConsumerMessage) {
        return KafkaConsumerMessageMO.builder()
                .offset(kafkaConsumerMessage.getOffset())
                .topic(kafkaConsumerMessage.getTopic())
                .receivedAt(kafkaConsumerMessage.getReceivedAt())
                .partition(kafkaConsumerMessage.getPartition())
                .key(gson.toJson(kafkaConsumerMessage.getKey()))
                .value(gson.toJson(kafkaConsumerMessage.getValue()))
                .header(gson.toJson(kafkaConsumerMessage.getHeader()))
                .build();
    }
    @SuppressWarnings("unchecked")
    public KafkaConsumerMessage<K, V> toDomain(KafkaConsumerMessageMO kafkaConsumerMessageMO) {
        return KafkaConsumerMessage.<K, V>builder()
                .offset(kafkaConsumerMessageMO.getOffset())
                .topic(kafkaConsumerMessageMO.getTopic())
                .receivedAt(kafkaConsumerMessageMO.getReceivedAt())
                .partition(kafkaConsumerMessageMO.getPartition())
                .key((K) gson.fromJson(kafkaConsumerMessageMO.getKey(), Object.class))
                .value((V) gson.fromJson(kafkaConsumerMessageMO.getValue(), Object.class))
                .header(gson.fromJson(kafkaConsumerMessageMO.getHeader(), Map.class))
                .build();
    }

}
