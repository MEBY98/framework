package com.meby98;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class KafkaAdminTopicService {

    private final AdminClient kafkaAdminClient;
    ;

    public KafkaAdminTopicService(@Qualifier("kafkaAdminClient") final AdminClient kafkaAdminClient) {
        this.kafkaAdminClient = kafkaAdminClient;
    }

    public boolean existsTopicByName(final String topicName) {
        try {
            final var topicNames = this.kafkaAdminClient.listTopics().names().get();
            return topicNames.stream().anyMatch(topicBrokerName -> topicBrokerName.equals(topicName));
        } catch (InterruptedException | ExecutionException e) {
            log.error("ERROR GETTING TOPICS FROM KAFKA BROKER", e);
            return false;
        }
    }

    public void createTopic(final Topic topic) {
        kafkaAdminClient.createTopics(List.of(topic.buildKafkaNewTopic()));
    }

    public List<Topic> getAll(final String name) {
        return this.topicDescriptions(topicNames(name)).stream().map(Topic::build).toList();
    }

    private Set<String> topicNames(final String name) {
        try {
            if (name != null && !name.isBlank()) {
                return this.kafkaAdminClient.listTopics().names().get().stream()
                        .filter(topicName -> topicName.contains(name)).collect(Collectors.toSet());
            }
            return this.kafkaAdminClient.listTopics().names().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("ERROR GETTING TOPICS NAMES FROM KAFKA BROKER", e);
            return Collections.emptySet();
        }
    }

    private List<TopicDescription> topicDescriptions(final Set<String> topicNames) {
        final var topicsMap = this.kafkaAdminClient.describeTopics(topicNames).topicNameValues();
        return topicsMap.values().stream().map(topicDescriptionKafkaFuture -> {
            try {
                return topicDescriptionKafkaFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error("ERROR GETTING TOPICS DESCRIPTIONS FROM KAFKA BROKER", e);
                return null;
            }
        }).filter(Objects::nonNull).toList();
    }

    public void deleteTopic(final String name) {
        this.kafkaAdminClient.deleteTopics(List.of(name));
    }
}
