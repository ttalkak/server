package com.ttalkak.compute.compute.adapter.out.cache.repository

import com.ttalkak.compute.compute.adapter.out.cache.entity.DeploymentStatusCache
import jakarta.annotation.Resource
import org.springframework.data.redis.core.HashOperations
import org.springframework.stereotype.Repository

@Repository
class DeploymentCacheRepository {
    @Resource(name = "redisTemplate")
    private lateinit var hashOperations: HashOperations<String, String, DeploymentStatusCache>

    companion object {
        const val DEPLOYMENT_CACHE_KEY = "deploymentCache"
    }

    fun save(deploymentId: Long, deploymentStatus: DeploymentStatusCache) {
        hashOperations.put(DEPLOYMENT_CACHE_KEY, deploymentId.toString(), deploymentStatus)
    }
}