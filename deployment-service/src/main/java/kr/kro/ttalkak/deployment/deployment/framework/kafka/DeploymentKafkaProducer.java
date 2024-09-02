package kr.kro.ttalkak.deployment.deployment.framework.kafka;

import kr.kro.ttalkak.deployment.deployment.domain.event.CreateInstanceEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class DeploymentKafkaProducer {

    @Value("${producers.topic1.name}")
    private String TOPIC_CREATE_INSTANCE;

    private final KafkaTemplate<String, CreateInstanceEvent> kafkaTemplate1;

    @KafkaListener(topics = "${consumer.topic1.name}" , groupId = "${consumer.groupid.name}")
    public void occurCreateInstance(CreateInstanceEvent createInstanceEvent) throws IOException {
        CompletableFuture<SendResult<String, CreateInstanceEvent>> future = kafkaTemplate1.send(TOPIC_CREATE_INSTANCE, createInstanceEvent);
        // 콜백 메서드 생성 해야함.
    }
}
