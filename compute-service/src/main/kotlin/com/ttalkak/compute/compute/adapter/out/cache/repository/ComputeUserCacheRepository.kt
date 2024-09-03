package com.ttalkak.compute.compute.adapter.out.cache.repository

import com.ttalkak.compute.compute.adapter.out.cache.entity.ComputeUserCache
import org.springframework.data.redis.core.HashOperations
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
class ComputeUserCacheRepository(
    private val hashOperations: HashOperations<String, Long, ComputeUserCache>
) {
    companion object {
        const val COMPUTE_CACHE_KEY = "computeUserCache"
    }

    fun save(computeUser: ComputeUserCache) {
        hashOperations.put(COMPUTE_CACHE_KEY, computeUser.userId, computeUser)
    }

    fun findById(userId: Long): Optional<ComputeUserCache> {
        return Optional.ofNullable(hashOperations.get(COMPUTE_CACHE_KEY, userId))
    }

    fun delete(userId: Long) {
        hashOperations.delete(COMPUTE_CACHE_KEY, userId)
    }

    fun exists(userId: Long): Boolean {
        return hashOperations.hasKey(COMPUTE_CACHE_KEY, userId)
    }
}