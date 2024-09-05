package com.ttalkak.compute.compute.application.port.out

data class StatusCommand(
    val maxCompute: Int,
    val availablePortStart: Int,
    val availablePortEnd: Int
)
