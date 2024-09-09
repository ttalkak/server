package com.ttalkak.compute.compute.application.port.`in`

import com.ttalkak.compute.compute.domain.ComputeRunning

data class RunningCommand (
    val deploymentId: Long,
    val status: ComputeRunning,
    val message: String
)