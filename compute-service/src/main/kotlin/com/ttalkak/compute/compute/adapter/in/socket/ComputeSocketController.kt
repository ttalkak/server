package com.ttalkak.compute.compute.adapter.`in`.socket

import com.ttalkak.compute.common.SocketAdapter
import com.ttalkak.compute.compute.adapter.`in`.socket.request.CreateComputeRequest
import com.ttalkak.compute.compute.application.port.`in`.ConnectCommand
import com.ttalkak.compute.compute.application.port.out.SaveComputePort
import com.ttalkak.compute.compute.application.service.ComputeSocketService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.web.bind.annotation.RequestMapping

@SocketAdapter
@RequestMapping("/compute")
class ComputeSocketController(
    private val computeSocketService: ComputeSocketService
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

        computeSocketService.connect(command)
    }
}