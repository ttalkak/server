package com.ttalkak.compute.compute.adapter.`in`.socket.request

data class DeploymentStatusRequest(
    val deploymentId: Long,
    val status: Boolean,
    val useMemory: Int,
    val useCPU: Double,
    val runningTime: Int,
    val diskRead: Double,
    val diskWrite: Double,
)
