package com.meby98.kafka;

import com.meby98.kafka.mappers.KafkaConsumerRecordMapper;
import com.meby98.kafka.mappers.KafkaConsumerRecordMapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.lang.Nullable;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

@Slf4j
public abstract class MercadonaKafkaAuditableAckConsumerListener<K, V> extends MercadonaKafkaBaseConsumerListener<K, V> {
  private final boolean auditError;
  private final boolean auditIn;

  @Autowired
  private SaveKafkaConsumerMessageRepositoryPort<K, V> saveKafkaConsumerMessageRepositoryPort;
  @Autowired
  private SaveKafkaConsumerErrorMessageRepositoryPort<K, V> saveKafkaConsumerErrorMessageRepositoryPort;

  private final KafkaConsumerRecordMapper<K, V> mapper = new KafkaConsumerRecordMapperImpl<>();

  protected MercadonaKafkaAuditableAckConsumerListener(final String[] topics, final String groupId, final Boolean auditIn, final Boolean auditError) {
    super(topics, groupId);
    this.auditIn = auditIn;
    this.auditError = auditError;
  }

  protected MercadonaKafkaAuditableAckConsumerListener(final MercadonaKafkaConsumerConfiguration config, final Boolean auditIn, final Boolean auditError) {
    super(config);
    this.auditIn = auditIn;
    this.auditError = auditError;
  }

  @KafkaListener(
      groupId = "#{__listener.groupId}",
      topics = {"#{__listener.topics}"},
      clientIdPrefix = "#{__listener.clientIdPrefix}",
      concurrency = "#{__listener.concurrency}",
      containerFactory = "#{__listener.containerFactory}"
  )
  protected void listener(@Payload(required = false) final ConsumerRecord<K, V> consumerRecord, final Acknowledgment ack) {
    try {
      log.info("READING MESSAGE WITH OFFSET: {} IN PARTITION: {} FROM TOPIC: {}", consumerRecord.offset(), consumerRecord.partition(), consumerRecord.topic());
      if (this.auditIn) {
        this.saveKafkaConsumerMessageRepositoryPort.save(this.mapper.toDomain(consumerRecord));
      }
      this.consume(consumerRecord);
      ack.acknowledge();
    } catch (final Exception exception) {
      log.error("ERROR IN MESSAGE WITH OFFSET: {} IN PARTITION: {} FROM TOPIC: {}", consumerRecord.offset(), consumerRecord.partition(), consumerRecord.topic());
      if (this.auditError) {
        this.saveKafkaConsumerErrorMessageRepositoryPort.save(this.mapper.toError(consumerRecord, exception.getMessage()));
        ack.acknowledge();
      } else {
        throw exception;
      }
    }
  }

  public void consume(final ConsumerRecord<K, V> consumerRecord) {
    this.consume(consumerRecord.key(), consumerRecord.value(), headers2map(consumerRecord.headers()));
  }

  public void consume(@Nullable final K key, @Nullable final V value, final Map<String, String> headers) {
    throw new UnsupportedOperationException();
  }
}
