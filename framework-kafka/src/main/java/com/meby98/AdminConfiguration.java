package com.meby98;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
@Slf4j
@EnableConfigurationProperties(AdminProperties.class)
@ConditionalOnProperty(prefix = "meby98.fwk.kafka", name = "enabled", havingValue = "true")
public class AdminConfiguration {

    @Bean
    @Qualifier("kafkaAdminClient")
    public AdminClient adminClient(final AdminProperties adminProperties) {
        return AdminClient.create(kafkaAdmin(adminProperties).getConfigurationProperties());
    }

    private KafkaAdmin kafkaAdmin(final AdminProperties adminProperties) {
        return new KafkaAdmin(adminProperties.asMap());
    }
}
