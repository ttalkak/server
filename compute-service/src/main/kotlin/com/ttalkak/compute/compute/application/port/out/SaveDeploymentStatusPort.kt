package com.ttalkak.compute.compute.application.port.out

interface SaveDeploymentStatusPort {
    fun saveDeploymentStatus(
        userId: Long,
        deploymentId: Long,
        status: String,
        useMemory: Int,
        useCPU: Double,
        runningTime: Int,
        diskRead: Double,
        diskWrite: Double,
    )
}