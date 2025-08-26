package com.meby98.kafka.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kafka_consumer_audit_error_message")
public class KafkaConsumerErrorMessageMO {
  @Id
  @Column(name = "message_offset")
  private Long offset;

  @Column(name = "message_topic")
  private String topic;

  @Column(name = "message_partition")
  private String partition;

  @Column(name = "received_at")
  private LocalDateTime receivedAt;

  @Column(name = "message_header")
  private String header;

  @Column(name = "message_key")
  private String key;

  @Column(name = "message_value")
  private String value;

  @Column(name = "message_exception")
  private String exception;
}
