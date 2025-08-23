package com.meby98.framework.kafka;

import com.mercadona.framework.cna.lib.kafka.consumer.MercadonaKafkaBatchBaseConsumerListener;
import com.mercadona.framework.cna.lib.kafka.consumer.MercadonaKafkaConsumerConfiguration;
import com.mercadona.supplychain.lib.common.kafka.mappers.KafkaConsumerRecordMapper;
import com.mercadona.supplychain.lib.common.kafka.mappers.KafkaConsumerRecordMapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.lang.Nullable;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;
import java.util.Map;

@Slf4j
public abstract class MercadonaKafkaAuditableBatchAckConsumerListener<K, V> extends MercadonaKafkaBatchBaseConsumerListener<K, V> {
  private final boolean auditIn;
  private final boolean auditError;

  @Autowired
  private SaveKafkaConsumerMessageRepositoryPort<K, V> saveKafkaConsumerMessageRepositoryPort;
  @Autowired
  private SaveKafkaConsumerErrorMessageRepositoryPort<K, V> saveKafkaConsumerErrorMessageRepositoryPort;

  private final KafkaConsumerRecordMapper<K, V> mapper = new KafkaConsumerRecordMapperImpl<>();

  protected MercadonaKafkaAuditableBatchAckConsumerListener(final String[] topics, final String groupId, final Boolean auditIn, final Boolean auditError) {
    super(topics, groupId);
    this.auditIn = auditIn;
    this.auditError = auditError;
  }

  protected MercadonaKafkaAuditableBatchAckConsumerListener(final MercadonaKafkaConsumerConfiguration config, final Boolean auditIn, final Boolean auditError) {
    super(config);
    this.auditIn = auditIn;
    this.auditError = auditError;
  }

  @KafkaListener(
      groupId = "#{__listener.groupId}",
      topics = {"#{__listener.topics}"},
      clientIdPrefix = "#{__listener.clientIdPrefix}",
      batch = "true",
      concurrency = "#{__listener.concurrency}",
      containerFactory = "#{__listener.containerFactory}"
  )
  protected void listener(@Payload(required = false) final List<ConsumerRecord<K, V>> listRecords, final Acknowledgment ack) {
    try {
      log.info("READING BATCH FROM: {} TO OFFSET: {}", listRecords.get(0).offset(), listRecords.get(listRecords.size() - 1).offset());
      if (this.auditIn) {
        this.saveKafkaConsumerMessageRepositoryPort.saveAll(listRecords.stream().map(this.mapper::toDomain).toList());
      }
      this.consume(listRecords);
      ack.acknowledge();
    } catch (Exception exception) {
      log.warn("ERROR WHEN PROCESSING IN BATCH, START PROCESS ONE BY ONE");
      listRecords.forEach(message -> {
        try {
          if (this.auditIn) {
            this.saveKafkaConsumerMessageRepositoryPort.save(this.mapper.toDomain(message));
          }
          this.consume(message);
        } catch (Exception e) {
          log.error("ERROR IN OFFSET: {}", message.offset(), e);
          if (auditError) {
            this.saveKafkaConsumerErrorMessageRepositoryPort.save(this.mapper.toError(message, e.getMessage()));
          } else {
            throw e;
          }
        }
      });
      ack.acknowledge();
    }
  }

  public void consume(final List<ConsumerRecord<K, V>> consumerRecords) {
    consumerRecords.forEach(this::consume);
  }

  public void consume(final ConsumerRecord<K, V> consumerRecord) {
    this.consume(consumerRecord.key(), consumerRecord.value(), headers2map(consumerRecord.headers()));
  }

  public void consume(@Nullable final K key, @Nullable final V value, final Map<String, String> headers) {
    throw new UnsupportedOperationException();
  }
}
