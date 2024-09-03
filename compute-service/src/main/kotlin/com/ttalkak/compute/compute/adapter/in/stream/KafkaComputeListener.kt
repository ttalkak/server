package com.ttalkak.compute.compute.adapter.`in`.stream

import com.ttalkak.compute.common.StreamAdapter
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.kafka.annotation.KafkaListener

@StreamAdapter
class KafkaComputeListener(
    private val redisTemplate: RedisTemplate<String, String>
) {
    companion object {
        const val COMPUTE_TOPIC = "create-compute"
    }
    
    @KafkaListener(topics = [COMPUTE_TOPIC])
    fun listen(message: String) {
        redisTemplate.convertAndSend(COMPUTE_TOPIC, message)
        println("KafkaComputeListener: $message")
    }
}