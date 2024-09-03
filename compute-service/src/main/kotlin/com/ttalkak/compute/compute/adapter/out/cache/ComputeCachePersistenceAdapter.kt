package com.ttalkak.compute.compute.adapter.out.cache

import com.ttalkak.compute.common.PersistenceAdapter
import com.ttalkak.compute.compute.adapter.out.cache.entity.ComputeUserCache
import com.ttalkak.compute.compute.adapter.out.cache.repository.ComputeUserCacheRepository
import com.ttalkak.compute.compute.application.port.out.LoadComputePort
import com.ttalkak.compute.compute.application.port.out.SaveComputePort
import com.ttalkak.compute.compute.domain.ComputerType

@PersistenceAdapter
class ComputeCachePersistenceAdapter(
    private val computeUserCacheRepository: ComputeUserCacheRepository
): SaveComputePort, LoadComputePort {
    override fun saveCompute(
        userId: Long,
        computeLimit: Int,
        availablePortStart: Int,
        availablePortEnd: Int,
        computeType: ComputerType,
        maxMemory: Int
    ) {
        val compute = ComputeUserCache(
            userId = userId,
            availableCompute = computeLimit,
            usedCompute = 0,
            availablePortStart = availablePortStart,
            availablePortEnd = availablePortEnd,
            computeType = computeType,
            maxMemory = maxMemory
        )

        computeUserCacheRepository.save(compute)
    }

    override fun deleteCompute(userId: Long) {
        computeUserCacheRepository.delete(userId)
    }

    override fun updateCompute(
        userId: Long,
        computeLimit: Int,
        availablePortStart: Int,
        availablePortEnd: Int,
        computeType: ComputerType,
        maxMemory: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun loadCompute(userId: Long) {
        TODO("Not yet implemented")
    }

    override fun isConnected(userId: Long): Boolean {
        return computeUserCacheRepository.exists(userId)
    }
}