package com.ttalkak.project.project.framework.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttalkak.project.project.application.usercase.DeleteProjectUseCase;
import com.ttalkak.project.project.application.usercase.UpdateProjectUseCase;
import com.ttalkak.project.project.domain.event.DeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteTransactionConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final DeleteProjectUseCase deleteProjectUseCase;

    @KafkaListener(topics = "${consumers.topic1.name}", groupId = "${consumers.groupid.name}")
    public void consumeRollBack(ConsumerRecord<String, String> record) throws IOException {
        log.info("consume roll back:{}", record.value());
        DeletedEvent deletedEvent = objectMapper.readValue(record.value(), DeletedEvent.class);
        deleteProjectUseCase.rollbackStatusProject(deletedEvent.getProjectId());
    }

}