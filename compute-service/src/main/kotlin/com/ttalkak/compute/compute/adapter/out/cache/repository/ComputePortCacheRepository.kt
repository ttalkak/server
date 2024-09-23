package com.ttalkak.compute.compute.adapter.out.cache.repository

import jakarta.annotation.Resource
import org.springframework.data.redis.core.ListOperations
import org.springframework.stereotype.Repository

@Repository
class ComputePortCacheRepository {
    @Resource(name = "redisTemplate")
    private lateinit var listOperations: ListOperations<String, Int>

    companion object {
        const val COMPUTE_PORT_CACHE_KEY = "computePortCache"
    }

    fun save(userId: Long, port: Int) {
        listOperations.leftPush(key(userId), port)
    }

    fun delete(userId: Long, port: Int) {
        listOperations.remove(key(userId), 1, port)
    }

    fun findAll(userId: Long): List<Int> {
        return listOperations.range(key(userId), 0, -1) ?: emptyList()
    }

    private fun key(userId: Long) = "$COMPUTE_PORT_CACHE_KEY:$userId"
}