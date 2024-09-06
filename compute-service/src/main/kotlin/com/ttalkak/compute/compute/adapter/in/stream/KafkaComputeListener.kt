package com.ttalkak.compute.compute.adapter.`in`.stream

import com.ttalkak.compute.common.StreamAdapter
import com.ttalkak.compute.common.util.Json
import com.ttalkak.compute.common.util.LoggerCreator
import com.ttalkak.compute.compute.adapter.`in`.socket.request.CreateComputeRequest
import com.ttalkak.compute.compute.domain.ComputeCreateEvent
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.annotation.PostConstruct
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate

@StreamAdapter
class KafkaComputeListener(
    private val redisTemplate: RedisTemplate<String, String>,
    private val redisMessageListenerContainer: RedisMessageListenerContainer,
    private val computeSocketListener: ComputeSocketListener
) {
    private val log = KotlinLogging.logger {}
    private val computeChannel = ChannelTopic("compute-create")

    @PostConstruct
    fun init() {
        redisMessageListenerContainer.addMessageListener(computeSocketListener, computeChannel)
    }

    @KafkaListener(topics = ["\${consumer.topics.compute-create.name}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun listen(@Payload record: String) {
        val response = Json.deserialize(record, ComputeCreateEvent::class.java)
        log.info {
            "컴퓨터 생성 이벤트 발생: ${response.deploymentId}"
        }
        redisTemplate.convertAndSend(computeChannel.topic, Json.serialize(response))
    }
}