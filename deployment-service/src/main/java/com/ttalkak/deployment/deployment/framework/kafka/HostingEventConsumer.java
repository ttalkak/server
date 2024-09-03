package com.ttalkak.deployment.deployment.framework.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttalkak.deployment.deployment.application.usecase.UpdateHostingUsecase;
import com.ttalkak.deployment.deployment.domain.event.HostingEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class HostingEventConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final UpdateHostingUsecase updateHostingUsecase;

    @KafkaListener(topics = "${consumers.topic1.name}", groupId = "${consumers.groupid.name}")
    public void consumeRental(ConsumerRecord<String, String> record) throws IOException {
        System.out.println("rental_rent:" + record.value());
        HostingEvent hostingEvent = objectMapper.readValue(record.value(), HostingEvent.class);
        updateHostingUsecase.updateHosting(hostingEvent);
    }
}
