package com.ttalkak.compute.compute.adapter.out.cache.repository

import com.ttalkak.compute.compute.adapter.out.cache.entity.ComputeUserCache
import jakarta.annotation.Resource
import org.springframework.data.redis.core.HashOperations
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
class ComputeUserCacheRepository{
    @Resource(name = "redisTemplate")
    private lateinit var hashOperations: HashOperations<String, Long, ComputeUserCache>

    companion object {
        const val COMPUTE_CACHE_KEY = "computeUserCache"
    }

    fun save(computeUser: ComputeUserCache) {
        hashOperations.put(COMPUTE_CACHE_KEY, computeUser.userId, computeUser)
    }

    fun findById(userId: Long): Optional<ComputeUserCache> {
        return Optional.ofNullable(hashOperations.get(COMPUTE_CACHE_KEY, userId.toString()))
    }

    fun delete(userId: Long) {
        hashOperations.delete(COMPUTE_CACHE_KEY, userId.toString())
    }

    fun findAll(): List<ComputeUserCache> {
        return hashOperations.entries(COMPUTE_CACHE_KEY).values.toList()
    }
}