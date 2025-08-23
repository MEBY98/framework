package com.meby98.framework.kafka;

import com.mercadona.framework.cna.commons.exception.MercadonaRuntimeException;
import com.mercadona.supplychain.lib.common.kafka.domain.KafkaConsumerErrorMessage;
import com.mercadona.supplychain.lib.common.kafka.mappers.KafkaConsumerErrorMessageMapper;
import com.mercadona.supplychain.lib.common.kafka.mappers.KafkaConsumerErrorMessageMapperImpl;
import com.mercadona.supplychain.lib.common.kafka.metrics.CountedDeadLetter;
import com.mercadona.supplychain.lib.common.kafka.model.KafkaConsumerErrorMessageMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Slf4j
public class KafkaConsumerErrorMessageRepositoryAdapter<K, V> implements SaveKafkaConsumerErrorMessageRepositoryPort<K, V> {
  private static final String INSERT_SQL = "INSERT INTO kafka_consumer_audit_error_message " +
      " (message_offset, message_topic, message_partition, received_at, message_header, message_key, message_value, message_exception) " +
      " VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
      " ON CONFLICT (message_offset, message_topic, message_partition) " +
      " DO UPDATE " +
      " SET received_at = EXCLUDED.received_at, message_header = EXCLUDED.message_header, message_key = EXCLUDED.message_key, message_value = EXCLUDED.message_value, message_exception = EXCLUDED.message_exception ";

  private final KafkaConsumerErrorMessageMapper<K, V> mapper = new KafkaConsumerErrorMessageMapperImpl<>();
  private final ConnectionConfig connectionConfig;

  public KafkaConsumerErrorMessageRepositoryAdapter(final ConnectionConfig connectionConfig) {
    this.connectionConfig = connectionConfig;
  }

  @Override
  @CountedDeadLetter(value = "ERROR_MESSAGE_CONSUMER")
  public void save(final KafkaConsumerErrorMessage<K, V> message) {
    try (final Connection connection = this.connectionConfig.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {

      final KafkaConsumerErrorMessageMO model = this.mapper.toModel(message);
      preparedStatement.setLong(1, model.getOffset());
      preparedStatement.setString(2, model.getTopic());
      preparedStatement.setString(3, model.getPartition());
      preparedStatement.setTimestamp(4, Timestamp.valueOf(model.getReceivedAt()));
      preparedStatement.setString(5, model.getHeader());
      preparedStatement.setString(6, model.getKey());
      preparedStatement.setString(7, model.getValue());
      preparedStatement.setString(8, model.getException());

      preparedStatement.execute();

    } catch (SQLException e) {
      log.error("ERROR CREATING INSERT STATEMENT IN MESSAGE WITH OFFSET: {}", message.getOffset());
      throw new MercadonaRuntimeException("ERROR CREATING INSERT STATEMENT IN MESSAGE WITH OFFSET: " + message.getOffset() + "\n" + e.getMessage());
    }
  }

  @Override
  public void saveAll(final List<KafkaConsumerErrorMessage<K, V>> messages) {
    try (final PreparedStatement preparedStatement = this.connectionConfig.getConnection().prepareStatement(INSERT_SQL)) {
      messages.forEach(message -> {
        final KafkaConsumerErrorMessageMO model = this.mapper.toModel(message);
        try {
          preparedStatement.setLong(1, model.getOffset());
          preparedStatement.setString(2, model.getTopic());
          preparedStatement.setString(3, model.getPartition());
          preparedStatement.setTimestamp(4, Timestamp.valueOf(model.getReceivedAt()));
          preparedStatement.setString(5, model.getHeader());
          preparedStatement.setString(6, model.getKey());
          preparedStatement.setString(7, model.getValue());
          preparedStatement.setString(8, model.getException());
          preparedStatement.addBatch();
        } catch (SQLException e) {
          log.error("ERROR CREATING INSERT BATCH IN MESSAGE WITH OFFSET: {}", message.getOffset());
          throw new MercadonaRuntimeException("ERROR CREATING INSERT BATCH IN MESSAGE WITH OFFSET: " + message.getOffset()+ "\n" + e.getMessage());
        }
      });
      preparedStatement.executeBatch();
    } catch (SQLException e) {
      throw new MercadonaRuntimeException("ERROR CREATING BATCH CONNECTION CHECK PROPERTIES: spring.datasource $url $password $username"+ "\n" + e.getMessage());
    }
  }
}
