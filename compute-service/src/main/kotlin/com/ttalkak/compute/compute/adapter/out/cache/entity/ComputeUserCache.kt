package com.ttalkak.compute.compute.adapter.out.cache.entity

import com.ttalkak.compute.compute.domain.ComputerType

data class ComputeUserCache (
    val userId: Long,
    val availableCompute: Int,
    val usedCompute: Int,
    val availablePortStart: Int,
    val availablePortEnd: Int,
    val computeType: ComputerType,
    val maxMemory: Int
)