package com.meby98;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
@Slf4j
@EnableConfigurationProperties(TopicProperties.class)
@ConditionalOnProperty(prefix = "meby98.fwk.kafka", name = "enabled", havingValue = "true")
public class TopicConfiguration {

    @Bean
    public KafkaAdmin.NewTopics createTopics(final TopicProperties topicProperties) {
        log.info("Creating topics: [{}].", topicProperties.getTopics().stream().map(Topic::name).toList());
        return new KafkaAdmin.NewTopics(topicProperties.getTopics().stream()
                .map(Topic::buildKafkaNewTopic).toList().toArray(new NewTopic[0]));
    }
}
