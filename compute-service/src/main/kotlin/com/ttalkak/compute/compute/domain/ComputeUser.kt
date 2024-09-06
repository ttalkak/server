package com.ttalkak.compute.compute.domain

data class ComputeUser(
    val userId: Long,
    val remainCompute: Int,
    val computeType: ComputerType,
    val remainMemory: Int
)
