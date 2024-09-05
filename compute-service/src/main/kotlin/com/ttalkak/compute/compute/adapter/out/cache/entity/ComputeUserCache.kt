package com.ttalkak.compute.compute.adapter.out.cache.entity

import com.ttalkak.compute.compute.domain.ComputerType

data class ComputeUserCache (
    val userId: Long,
    val usedCompute: Int,
    val computeType: ComputerType,
    val maxMemory: Int
)