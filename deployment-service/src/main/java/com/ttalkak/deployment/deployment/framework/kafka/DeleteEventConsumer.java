package com.ttalkak.deployment.deployment.framework.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttalkak.deployment.deployment.application.inputport.DeleteDeploymentInputPort;
import com.ttalkak.deployment.deployment.domain.event.DeleteDeploymentsEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DeleteEventConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final DeleteDeploymentInputPort deleteDeploymentInputPort;

    @KafkaListener(topics = "${consumers.topic2.name}", groupId = "project-deletion-service")
    public void deleteConsumer(ConsumerRecord<String, String> record) throws IOException {
        DeleteDeploymentsEvent deleteDeploymentsEvent = objectMapper.readValue(record.value(), DeleteDeploymentsEvent.class);
        deleteDeploymentInputPort.deleteDeploymentByProject(deleteDeploymentsEvent.getProjectId());
    }
}
