package com.ttalkak.project.project.framework.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttalkak.project.project.application.inputport.DeleteProjectInputPort;
import com.ttalkak.project.project.application.outputport.EventOutputPort;
import com.ttalkak.project.project.domain.event.ProjectEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectKafkaProducer implements EventOutputPort {

    @Value("${producers.topic1.name}")
    private String TOPIC_DELETE_PROJECT;

    private final KafkaTemplate<String, String> kafkaTemplate1;

    @Override
    public void occurDeleteDeploymentInstance(ProjectEvent projectEvent) throws JsonProcessingException {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate1.send(TOPIC_DELETE_PROJECT, projectEvent.toString());
        future.thenAccept(result -> {
            String value = result.getProducerRecord().value();
            log.info("Sent message=[{}] with offset=[{}]", value, result.getRecordMetadata().offset());
        }).exceptionally(ex ->{
            throw new IllegalArgumentException(ex);
        });
    }
}
