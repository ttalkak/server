package com.ttalkak.deployment.deployment.framework.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DeleteEventConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "project_deletion_initiated", groupId = "project-deletion-service")
    public void deleteConsumer(ConsumerRecord<String, String> consumerRecord) throws IOException {
        System.out.println(consumerRecord.value() + "============================ 삭제 요청 받음 ");
    }
}
