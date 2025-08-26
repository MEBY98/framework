package com.meby98.kafka.mappers;

import com.meby98.kafka.domain.KafkaConsumerErrorMessage;
import com.meby98.kafka.domain.KafkaConsumerMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class KafkaConsumerRecordMapperImpl<K, V> implements KafkaConsumerRecordMapper<K, V> {

    public KafkaConsumerMessage<K, V> toDomain(ConsumerRecord<K, V> consumerRecord) {
        return KafkaConsumerMessage.<K, V>builder()
                .offset(this.offset(consumerRecord))
                .topic(this.topic(consumerRecord))
                .partition(this.partition(consumerRecord))
                .receivedAt(this.localDateTimeNow())
                .header(headers(consumerRecord))
                .key(key(consumerRecord))
                .value(value(consumerRecord))
                .build();
    }

    @Override
    public KafkaConsumerErrorMessage<K, V> toError(final ConsumerRecord<K, V> consumerRecord, final String exception) {
        return (KafkaConsumerErrorMessage<K, V>) KafkaConsumerErrorMessage.<K, V>builder()
            .exception(exception)
            .offset(this.offset(consumerRecord))
            .topic(this.topic(consumerRecord))
            .partition(this.partition(consumerRecord))
            .receivedAt(this.localDateTimeNow())
            .header(headers(consumerRecord))
            .key(key(consumerRecord))
            .value(value(consumerRecord))
            .build();
    }

    @Override
    public KafkaConsumerErrorMessage<K, V> toError(
        final KafkaConsumerMessage<K, V> message,
        final String exception) {
        return (KafkaConsumerErrorMessage<K, V>) KafkaConsumerErrorMessage.<K, V>builder()
            .exception(exception)
            .offset(message.getOffset())
            .topic(message.getTopic())
            .partition(message.getPartition())
            .receivedAt(message.getReceivedAt())
            .header(message.getHeader())
            .key(message.getKey())
            .value(message.getValue())
            .build();
    }

    private LocalDateTime localDateTimeNow() {
        return LocalDateTime.now();
    }

    private Map<String, String> headers(ConsumerRecord<K, V> consumerRecord) {
        return MercadonaKafkaBaseConsumerListener.headers2map(consumerRecord.headers());
    }

    private Long offset(final ConsumerRecord<K, V> consumerRecord) { return consumerRecord.offset(); }
    private K key(ConsumerRecord<K, V> consumerRecord) {
        return consumerRecord.key();
    }
    private V value(ConsumerRecord<K, V> consumerRecord) {
        return consumerRecord.value();
    }
    private String topic(ConsumerRecord<K, V> consumerRecord) {
        return consumerRecord.topic();
    }
    private String partition(ConsumerRecord<K, V> consumerRecord) {
        return String.valueOf(consumerRecord.partition());
    }
}
