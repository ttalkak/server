package com.ttalkak.compute.compute.adapter.`in`.stream

import com.ttalkak.compute.common.StreamAdapter
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.kafka.annotation.KafkaListener

@StreamAdapter
class KafkaComputeListener(
    private val redisTemplate: RedisTemplate<String, String>
) {
    @KafkaListener(topics = ["\${consumer.topics.user-create.name}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun listen(message: String) {
        redisTemplate.convertAndSend("", message)
        println("KafkaComputeListener: $message")
    }
}