package com.ttalkak.deployment.deployment.framework.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttalkak.deployment.common.global.error.ErrorCode;
import com.ttalkak.deployment.common.global.exception.BusinessException;
import com.ttalkak.deployment.deployment.application.outputport.EventOutputPort;
import com.ttalkak.deployment.deployment.domain.event.CreateDatabaseEvent;
import com.ttalkak.deployment.deployment.domain.event.CreateInstanceEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class DeploymentKafkaProducer implements EventOutputPort {

    @Value("${producers.topics.create-compute.name}")
    private String TOPIC_CREATE_INSTANCE;

    @Value("${producers.topics.rebuild-compute.name}")
    private String TOPIC_REBUILD_INSTANCE;

    @Value("${producers.topics.create-database.name}")
    private String TOPIC_CREATE_DATABASE;

    private final KafkaTemplate<String, CreateInstanceEvent> kafkaTemplate;

    private final KafkaTemplate<String, CreateDatabaseEvent> kafkaTemplateDatabase;

    private final Logger LOGGER = LoggerFactory.getLogger(DeploymentKafkaProducer.class);

    @Override
    public void occurCreateInstance(CreateInstanceEvent createInstanceEvent) throws JsonProcessingException {

        CompletableFuture<SendResult<String, CreateInstanceEvent>> future = kafkaTemplate.send(TOPIC_CREATE_INSTANCE, createInstanceEvent);
        // 콜백 메서드 생성 해야함.
        future.thenAccept(result -> {
            CreateInstanceEvent value = result.getProducerRecord().value();
            LOGGER.info("Sent message=[" + value.getDeploymentId() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
        }).exceptionally(ex ->{
            throw new BusinessException(ErrorCode.KAFKA_CREATE_INSTANCE_PRODUCER_ERROR);
        });
    }

    @Override
    public void occurRebuildInstance(CreateInstanceEvent createInstanceEvent) throws JsonProcessingException {
        CompletableFuture<SendResult<String, CreateInstanceEvent>> future = kafkaTemplate.send(TOPIC_REBUILD_INSTANCE, createInstanceEvent);
        // 콜백 메서드 생성 해야함.
        future.thenAccept(result -> {
            CreateInstanceEvent value = result.getProducerRecord().value();
            LOGGER.info("Sent message=[" + value.getDeploymentId() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
        }).exceptionally(ex ->{
            throw new BusinessException(ErrorCode.KAFKA_REBUILD_INSTANCE_PRODUCER_ERROR);
        });
    }

    @Override
    public void occurCreateDatabase(CreateDatabaseEvent createDatabaseEvent) throws JsonProcessingException {
        CompletableFuture<SendResult<String, CreateDatabaseEvent>> future = kafkaTemplateDatabase.send(TOPIC_CREATE_DATABASE, createDatabaseEvent);
        // 콜백 메서드 생성 해야함.
        future.thenAccept(result -> {
            CreateDatabaseEvent value = result.getProducerRecord().value();
            LOGGER.info("Sent message=[" + value.getPort() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
        }).exceptionally(ex ->{
            throw new BusinessException(ErrorCode.KAFKA_CREATE_DATABASE_PRODUCER_ERROR);
        });
    }


}
