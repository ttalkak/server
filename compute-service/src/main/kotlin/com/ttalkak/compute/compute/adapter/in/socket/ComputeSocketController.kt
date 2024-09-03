package com.ttalkak.compute.compute.adapter.`in`.socket

import com.ttalkak.compute.common.SocketAdapter
import com.ttalkak.compute.compute.adapter.`in`.socket.request.CreateComputeRequest
import com.ttalkak.compute.compute.application.port.`in`.ConnectCommand
import com.ttalkak.compute.compute.application.service.ComputeService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.web.bind.annotation.RequestMapping

@SocketAdapter
@RequestMapping("/compute")
class ComputeSocketController(
    private val computeService: ComputeService
) {
    @MessageMapping("/connect")
    fun compute(request: CreateComputeRequest) {
        val command = ConnectCommand(
            userId = request.userId,
            computeLimit = request.computeLimit,
            availablePortStart = request.availablePortStart,
            availablePortEnd = request.availablePortEnd,
            computeType = request.computeType,
            maxMemory = request.maxMemory
        )

        computeService.connect(command)
    }
}