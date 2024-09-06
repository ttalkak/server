package com.ttalkak.user.user.adapter.out.stream;

import com.ttalkak.user.common.StreamAdapter;
import com.ttalkak.user.user.application.port.out.UserCreatePort;
import com.ttalkak.user.user.domain.UserCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@StreamAdapter
@RequiredArgsConstructor
@Slf4j
public class UserCreateProducer implements UserCreatePort {
    private final KafkaTemplate<String, UserCreateEvent> kafkaTemplate;

    @Value("${producer.topics.user-create.name}")
    private String topic;

    @Override
    public void createUser(UserCreateEvent userCreateEvent) {
        CompletableFuture<SendResult<String, UserCreateEvent>> future = kafkaTemplate.send(topic, userCreateEvent);
        future.thenAccept(response -> {
            UserCreateEvent value = response.getProducerRecord().value();
            log.debug("Sent message=[{}] with offset=[{}]", value, response.getRecordMetadata().offset());
        }).exceptionally(error -> {
            log.error("Unable to send message=[{}] due to : {}", userCreateEvent, error.getMessage());
            return null;
        });
    }
}