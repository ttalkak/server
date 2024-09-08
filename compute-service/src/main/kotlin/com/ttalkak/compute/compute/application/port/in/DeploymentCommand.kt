package com.ttalkak.compute.compute.application.port.`in`

data class DeploymentCommand(
    val deploymentId: Long,
    val status: Boolean,
    val useMemory: Int,
    val useCPU: Double,
    val runningTime: Int,
    val diskRead: Double,
    val diskWrite: Double,
)
