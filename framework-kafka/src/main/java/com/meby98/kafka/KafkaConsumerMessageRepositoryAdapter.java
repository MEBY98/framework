package com.meby98.kafka;

import com.meby98.kafka.domain.KafkaConsumerMessage;
import com.meby98.kafka.mappers.KafkaConsumerMessageMapper;
import com.meby98.kafka.mappers.KafkaConsumerMessageMapperImpl;
import com.meby98.kafka.model.KafkaConsumerMessageMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Slf4j
public class KafkaConsumerMessageRepositoryAdapter<K, V> implements SaveKafkaConsumerMessageRepositoryPort<K, V> {
    private static final String INSERT_SQL = "INSERT INTO kafka_consumer_audit_message " +
        " (message_offset, message_topic, message_partition, received_at, message_header, message_key, message_value) " +
        " VALUES (?, ?, ?, ?, ?, ?, ?) " +
        " ON CONFLICT (message_offset, message_topic, message_partition) " +
        " DO UPDATE " +
        " SET received_at = EXCLUDED.received_at, message_header = EXCLUDED.message_header, message_key = EXCLUDED.message_key, message_value = EXCLUDED.message_value ";

    private final KafkaConsumerMessageMapper<K, V> mapper = new KafkaConsumerMessageMapperImpl<>();
    private final ConnectionConfig connectionConfig;

    public KafkaConsumerMessageRepositoryAdapter (final ConnectionConfig connectionConfig) {
        this.connectionConfig = connectionConfig;
    }

    @Override
    public void save(final KafkaConsumerMessage<K, V> message) {
        try (final Connection connection = this.connectionConfig.getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {

            final KafkaConsumerMessageMO model = this.mapper.toModel(message);
            preparedStatement.setLong(1, model.getOffset());
            preparedStatement.setString(2, model.getTopic());
            preparedStatement.setString(3, model.getPartition());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(model.getReceivedAt()));
            preparedStatement.setString(5, model.getHeader());
            preparedStatement.setString(6, model.getKey());
            preparedStatement.setString(7, model.getValue());

            preparedStatement.execute();

        } catch (SQLException e) {
            log.error("ERROR CREATING INSERT STATEMENT IN MESSAGE WITH OFFSET: {}", message.getOffset());
            throw new RuntimeException("ERROR CREATING INSERT STATEMENT IN MESSAGE WITH OFFSET: " + message.getOffset());
        }
    }

    @Override
    public void saveAll(final List<KafkaConsumerMessage<K, V>> messages) {
        try (final Connection connection = this.connectionConfig.getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            messages.forEach(message -> {
                final KafkaConsumerMessageMO model = this.mapper.toModel(message);
                try {
                    preparedStatement.setLong(1, model.getOffset());
                    preparedStatement.setString(2, model.getTopic());
                    preparedStatement.setString(3, model.getPartition());
                    preparedStatement.setTimestamp(4, Timestamp.valueOf(model.getReceivedAt()));
                    preparedStatement.setString(5, model.getHeader());
                    preparedStatement.setString(6, model.getKey());
                    preparedStatement.setString(7, model.getValue());
                    preparedStatement.addBatch();
                } catch (SQLException e) {
                    log.error("ERROR CREATING INSERT BATCH IN MESSAGE WITH OFFSET: {}", message.getOffset());
                    throw new RuntimeException("ERROR CREATING INSERT BATCH IN MESSAGE WITH OFFSET: " + message.getOffset());
                }
            });
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("ERROR CREATING BATCH CONNECTION CHECK PROPERTIES: spring.datasource $url $password $username");
        }
    }
}
