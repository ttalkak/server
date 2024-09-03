package com.ttalkak.compute.compute.application.service

import com.ttalkak.compute.common.UseCase
import com.ttalkak.compute.compute.application.port.`in`.ComputeListener
import com.ttalkak.compute.compute.application.port.`in`.ConnectCommand
import com.ttalkak.compute.compute.application.port.out.LoadComputePort
import com.ttalkak.compute.compute.application.port.out.SaveComputePort

@UseCase
class ComputeSocketService(
    private val saveComputePort: SaveComputePort,
    private val loadComputePort: LoadComputePort
): ComputeListener {
    override fun connect(command: ConnectCommand) {
        if (loadComputePort.isConnected(command.userId)) {
            throw IllegalArgumentException("이미 연결된 사용자입니다.")
        }

        saveComputePort.saveCompute(
            userId = command.userId,
            computeLimit = command.computeLimit,
            availablePortStart = command.availablePortStart,
            availablePortEnd = command.availablePortEnd,
            computeType = command.computeType,
            maxMemory = command.maxMemory
        )
    }

    override fun disconnect(userId: Long) {
        if (!loadComputePort.isConnected(userId)) {
            throw IllegalArgumentException("연결되지 않은 사용자입니다.")
        }

        saveComputePort.deleteCompute(userId)
    }
}