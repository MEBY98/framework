package com.meby98.framework.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kafka_consumer_audit_message")
public class KafkaConsumerMessageMO {
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
}
