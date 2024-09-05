package com.ttalkak.compute.compute.adapter.`in`.socket

import com.ttalkak.compute.common.SocketAdapter
import com.ttalkak.compute.compute.adapter.`in`.socket.request.CreateComputeRequest
import com.ttalkak.compute.compute.application.port.`in`.ConnectCommand
import com.ttalkak.compute.compute.application.service.ComputeService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@SocketAdapter
@Controller
class ComputeSocketController(
    private val computeListener: ComputeService
) {
    @MessageMapping("/compute/connect")
    fun compute(@Payload request: CreateComputeRequest) {
        val command = ConnectCommand(
            userId = request.userId,
            computeType = request.computeType,
            maxMemory = request.maxMemory
        )

        computeListener.connect(command)
    }
}