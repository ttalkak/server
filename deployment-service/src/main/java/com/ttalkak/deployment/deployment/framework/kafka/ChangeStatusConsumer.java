package com.ttalkak.deployment.deployment.framework.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttalkak.deployment.deployment.application.usecase.UpdateDeploymentStatusUsecase;
import com.ttalkak.deployment.deployment.domain.model.vo.DeploymentStatus;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentUpdateStatusRequest;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class ChangeStatusConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final UpdateDeploymentStatusUsecase updateDeploymentStatusUsecase;

    @KafkaListener(topics = "${consumers.topic.update-deployment-status}", groupId = "consumers-groupid-name")
    public void deleteConsumer(ConsumerRecord<String, String> record) throws IOException {
        DeploymentUpdateStatusRequest deploymentUpdateStatusRequest = objectMapper.readValue(record.value(), DeploymentUpdateStatusRequest.class);
        updateDeploymentStatusUsecase.updateDeploymentStatus(deploymentUpdateStatusRequest);
    }
}