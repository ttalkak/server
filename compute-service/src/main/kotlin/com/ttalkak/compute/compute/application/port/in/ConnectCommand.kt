package com.ttalkak.compute.compute.application.port.`in`

import com.ttalkak.compute.compute.domain.ComputerType

data class ConnectCommand(
    val userId: Long,
    val computeLimit: Int,
    val availablePortStart: Int,
    val availablePortEnd: Int,
    val computeType: ComputerType,
    val maxMemory: Int
)
