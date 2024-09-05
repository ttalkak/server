package com.ttalkak.compute.compute.application.service

import com.ttalkak.compute.common.UseCase
import com.ttalkak.compute.compute.application.port.`in`.ComputeUseCase
import com.ttalkak.compute.compute.application.port.`in`.ConnectCommand
import com.ttalkak.compute.compute.application.port.out.CheckConnectPort
import com.ttalkak.compute.compute.application.port.out.RemoveConnectPort
import com.ttalkak.compute.compute.application.port.out.SaveComputePort

@UseCase
class ComputeService(
    private val saveComputePort: SaveComputePort,
    private val checkConnectPort: CheckConnectPort,
    private val removeConnectPort: RemoveConnectPort
): ComputeUseCase {
    override fun connect(command: ConnectCommand) {
        if (checkConnectPort.isConnected(command.userId)) {
            throw IllegalArgumentException("이미 연결된 사용자입니다.")
        }

        saveComputePort.saveCompute(
            userId = command.userId,
            computeType = command.computeType,
            maxMemory = command.maxMemory
        )
    }

    override fun disconnect(userId: Long) {
        if (!checkConnectPort.isConnected(userId)) {
            throw IllegalArgumentException("연결되지 않은 사용자입니다.")
        }

        removeConnectPort.disconnect(userId)
        saveComputePort.deleteCompute(userId)
    }
}