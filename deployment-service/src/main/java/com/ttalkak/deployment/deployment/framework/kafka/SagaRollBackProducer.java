package com.ttalkak.deployment.deployment.framework.kafka;


import com.ttalkak.deployment.deployment.domain.event.DeleteDeploymentsEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class SagaRollBackProducer {

    @Value("${producers.topic.project-deletion-exception.name}")
    private String TOPIC_ROllBACK_INSTANCE;

    private final KafkaTemplate<String, DeleteDeploymentsEvent> kafkaTemplate;

    public void rollbackDeleteStatus(DeleteDeploymentsEvent deleteDeploymentsEvent) {
        CompletableFuture<SendResult<String, DeleteDeploymentsEvent>> future = kafkaTemplate.send(TOPIC_ROllBACK_INSTANCE, deleteDeploymentsEvent);

        future.thenAccept(result -> {
            log.info("롤백 - 프로젝트 ID {} 배포 삭제 실패", deleteDeploymentsEvent.getProjectId());
        }).exceptionally(ex -> {
            throw new IllegalArgumentException(ex);
        });
    }
}