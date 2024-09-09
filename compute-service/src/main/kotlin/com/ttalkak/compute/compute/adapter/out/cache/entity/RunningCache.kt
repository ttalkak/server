package com.ttalkak.compute.compute.adapter.out.cache.entity

import com.ttalkak.compute.compute.domain.ComputeRunning

data class RunningCache(
    val status: ComputeRunning,
    val message: String
)
