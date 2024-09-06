package com.ttalkak.deployment.deployment.framework.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttalkak.deployment.deployment.application.outputport.EventOutputPort;
import com.ttalkak.deployment.deployment.domain.event.CreateInstanceEvent;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class DeploymentKafkaProducer implements EventOutputPort {

    @Value("${producers.topic.create-instance.name}")
    private String TOPIC_CREATE_INSTANCE;

    private final KafkaTemplate<String, CreateInstanceEvent> kafkaTemplate1;

    private final Logger LOGGER = LoggerFactory.getLogger(DeploymentKafkaProducer.class);

    @Autowired
    private Environment env;

    @Override
    public void occurCreateInstance(CreateInstanceEvent createInstanceEvent) throws JsonProcessingException {

        CompletableFuture<SendResult<String, CreateInstanceEvent>> future = kafkaTemplate1.send(TOPIC_CREATE_INSTANCE, createInstanceEvent);
        // 콜백 메서드 생성 해야함.
        future.thenAccept(result -> {
            CreateInstanceEvent value = result.getProducerRecord().value();
            LOGGER.info("Sent message=[" + value.getDeploymentId() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
        }).exceptionally(ex ->{
            throw new IllegalArgumentException(ex);
        });
    }
}
