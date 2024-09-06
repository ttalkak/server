package com.ttalkak.compute.compute.adapter.out.cache

import com.ttalkak.compute.common.PersistenceAdapter
import com.ttalkak.compute.compute.adapter.out.cache.entity.ComputeUserCache
import com.ttalkak.compute.compute.adapter.out.cache.repository.ComputeUserCacheRepository
import com.ttalkak.compute.compute.application.port.out.LoadComputePort
import com.ttalkak.compute.compute.application.port.out.SaveComputePort
import com.ttalkak.compute.compute.domain.ComputeUser
import com.ttalkak.compute.compute.domain.ComputerType
import java.util.*

@PersistenceAdapter
class ComputeCachePersistenceAdapter(
    private val computeUserCacheRepository: ComputeUserCacheRepository
): SaveComputePort, LoadComputePort {
    override fun saveCompute(
        userId: Long,
        computeType: ComputerType,
        maxMemory: Int
    ) {
        val compute = ComputeUserCache(
            userId = userId,
            usedCompute = 0,
            computeType = computeType,
            maxMemory = maxMemory
        )

        computeUserCacheRepository.save(compute)
    }

    override fun deleteCompute(userId: Long) {
        computeUserCacheRepository.delete(userId)
    }

    override fun loadCompute(userId: Long): Optional<ComputeUser> {
        return computeUserCacheRepository.findById(userId).map {
                ComputeUser(
                    userId = it.userId,
                    remainCompute = it.usedCompute,
                    computeType = it.computeType,
                    remainMemory = it.maxMemory
                )
            }
    }

    override fun loadAllCompute(): List<ComputeUser> {
        return computeUserCacheRepository.findAll().map {
            ComputeUser(
                userId = it.userId,
                remainCompute = it.usedCompute,
                computeType = it.computeType,
                remainMemory = it.maxMemory
            )
        }
    }
}