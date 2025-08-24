package com.meby98;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.kafka.clients.admin.TopicListing;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "meby98.fwk.kafka")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  TopicProperties {
    private boolean enabled = true;
    private List<Topic> topics = new ArrayList<>();

    public TopicProperties(final TopicListing topicListing) {

    }
}
