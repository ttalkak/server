package com.ttalkak.compute.compute.adapter.out.cache.entity

data class DeploymentStatusCache (
    val status: Boolean,
    val useMemory: Int,
    val useCPU: Double,
    val runningTime: Int,
    val diskRead: Double,
    val diskWrite: Double,
)
