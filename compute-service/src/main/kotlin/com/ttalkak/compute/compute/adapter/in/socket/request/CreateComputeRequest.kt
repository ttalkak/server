package com.ttalkak.compute.compute.adapter.`in`.socket.request

import com.ttalkak.compute.compute.domain.ComputerType

data class CreateComputeRequest(
    val userId: Long,
    val computeLimit: Int,
    val availablePortStart: Int,
    val availablePortEnd: Int,
    val computeType: ComputerType,
    val maxMemory: Int
)
