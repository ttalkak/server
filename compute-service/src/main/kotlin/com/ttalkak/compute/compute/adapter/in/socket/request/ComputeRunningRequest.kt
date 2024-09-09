package com.ttalkak.compute.compute.adapter.`in`.socket.request

import com.ttalkak.compute.compute.domain.ComputeRunning

data class ComputeRunningRequest(
    val status: ComputeRunning,
    val message: String
)
