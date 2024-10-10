package com.ttalkak.compute.compute.adapter.out.cache.repository

import com.ttalkak.compute.common.util.Json
import com.ttalkak.compute.compute.adapter.out.cache.entity.ComputeAllocateCache
import com.ttalkak.compute.compute.domain.ServiceType
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.annotation.Resource
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.ZSetOperations
import org.springframework.stereotype.Repository

@Repository
class InstanceCacheRepository {
    @Resource(name = "redisTemplate")
    private lateinit var hashOperations: HashOperations<String, String, String>

    private val log = KotlinLogging.logger {}

    companion object {
        const val INSTANCE_CACHE_KEY = "instanceCache"
    }

    fun add(serviceId: Long, serviceType: ServiceType, instance: ComputeAllocateCache) {
        val value = Json.serialize(instance)
        hashOperations.put(INSTANCE_CACHE_KEY, key(serviceId, serviceType), value)
    }

    fun get(serviceId: Long, serviceType: ServiceType): ComputeAllocateCache? {
        val value = hashOperations.get(INSTANCE_CACHE_KEY, key(serviceId, serviceType))
        return value?.let {
            Json.deserialize(it, ComputeAllocateCache::class.java)
        }
    }

    fun remove(serviceId: Long, serviceType: ServiceType) {
        hashOperations.delete(INSTANCE_CACHE_KEY, key(serviceId, serviceType))
    }

    private fun key(serviceId: Long, serviceType: ServiceType) = "$serviceId-${serviceType}"

}