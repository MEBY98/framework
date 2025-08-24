package com.meby98;

import lombok.*;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminProperties {
    @Value("${meby98.fwk.kafka.admin.bootstrap.servers}")
    private String bootstrapServers;
    @Value("${meby98.fwk.kafka.admin.client.id}")
    private String clientId;

    public Map<String, Object> asMap() {
        return Map.of(
                AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                AdminClientConfig.CLIENT_ID_CONFIG, clientId
        );
    }
}
