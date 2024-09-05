package com.ttalkak.compute.compute.adapter.`in`.web.request

data class CreateStatusRequest(
    val maxCompute: Int,
    val availablePortStart: Int,
    val availablePortEnd: Int
)
