package com.meby98.framework.kafka.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class KafkaConsumerMessage<K, V> {
    private final Long offset;
    private final String topic;
    private final String partition;
    private final LocalDateTime receivedAt;
    private final Map<String, String> header;
    private final K key;
    private final V value;

    public KafkaConsumerMessage(final KafkaConsumerMessageBuilder<K, V> builder) {
        this.offset = builder.offset;
        this.topic = builder.topic;
        this.partition = builder.partition;
        this.receivedAt = builder.receivedAt;
        this.key = builder.key;
        this.value = builder.value;
        this.header = builder.header;
    }

    public static <K, V> KafkaConsumerMessageBuilder<K, V> builder() {
        return new KafkaConsumerMessageBuilder<>();
    }

    public static class KafkaConsumerMessageBuilder<K, V> {
        private Long offset;
        private String topic;
        private String partition;
        private LocalDateTime receivedAt;
        private Map<String, String> header;
        private K key;
        private V value;

        public KafkaConsumerMessageBuilder<K, V> offset(final Long offset) {
            this.offset = offset;
            return this;
        }
        public KafkaConsumerMessageBuilder<K, V> topic(final String topic) {
            this.topic = topic;
            return this;
        }
        public KafkaConsumerMessageBuilder<K, V> partition(final String partition) {
            this.partition = partition;
            return this;
        }
        public KafkaConsumerMessageBuilder<K, V> receivedAt(final LocalDateTime receivedAt) {
            this.receivedAt = receivedAt;
            return this;
        }
        public KafkaConsumerMessageBuilder<K, V> key(final K key) {
            this.key = key;
            return this;
        }
        public KafkaConsumerMessageBuilder<K, V> value(final V value) {
            this.value = value;
            return this;
        }
        public KafkaConsumerMessageBuilder<K, V> header(final Map<String, String> header) {
            this.header = header;
            return this;
        }

        public KafkaConsumerMessage<K, V> build() {
            return new KafkaConsumerMessage<>(this);
        }
    }
}
