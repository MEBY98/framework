package com.meby98;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/kafka/topics")
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(prefix = "meby98.fwk.kafka.rest-proxy", name = "enabled", havingValue = "true")
public class KafkaAdminRestController {

    private final KafkaAdminTopicService kafkaAdminTopicService;

    @PostMapping
    public ResponseEntity<String> createTopic(@RequestBody final Topic topicProperties) {
        if (kafkaAdminTopicService.existsTopicByName(topicProperties.name())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TOPIC ALREADY EXISTS");
        } else {
            this.kafkaAdminTopicService.createTopic(topicProperties);
            return ResponseEntity.status(HttpStatus.CREATED).body("TOPIC CREATED");
        }
    }

    @GetMapping
    public ResponseEntity<List<Topic>> getTopics(@RequestParam(value = "name", name = "name", required = false) final String name) {
        return ResponseEntity.ok(this.kafkaAdminTopicService.getAll(name));
    }

    @GetMapping("/{name}")
    public ResponseEntity<TopicDetail> getTopic(@PathVariable("name") final String name) {
        if (kafkaAdminTopicService.existsTopicByName(name)) {
            return ResponseEntity.status(HttpStatus.OK).body(this.kafkaAdminTopicService.getOneByName(name));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteTopic(@PathVariable("name") final String name) {
        if (kafkaAdminTopicService.existsTopicByName(name)) {
            this.kafkaAdminTopicService.deleteTopic(name);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("TOPIC DELETED");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TOPIC NOT EXISTS");
        }
    }
}
