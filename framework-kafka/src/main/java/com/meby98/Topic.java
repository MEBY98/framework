package com.meby98;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;

public record Topic(String name, int partitions, short replicaFactor) {

    public NewTopic buildKafkaNewTopic() {
        return new NewTopic(this.name, this.partitions, this.replicaFactor);
    }

    public static Topic build(final TopicDescription topicDescription) {
        return new Topic(topicDescription.name(), topicDescription.partitions().size(), (short) 0);
    }
}
