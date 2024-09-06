package com.ttalkak.compute.compute.application.service

import com.ttalkak.compute.common.UseCase
import com.ttalkak.compute.compute.application.port.`in`.AllocateCommand
import com.ttalkak.compute.compute.application.port.`in`.AllocateUseCase
import com.ttalkak.compute.compute.application.port.`in`.ComputeUseCase
import com.ttalkak.compute.compute.application.port.`in`.ConnectCommand
import com.ttalkak.compute.compute.application.port.out.CheckConnectPort
import com.ttalkak.compute.compute.application.port.out.LoadComputePort
import com.ttalkak.compute.compute.application.port.out.RemoveConnectPort
import com.ttalkak.compute.compute.application.port.out.SaveComputePort

@UseCase
class ComputeService (
    private val saveComputePort: SaveComputePort,
    private val removeConnectPort: RemoveConnectPort,
    private val loadComputePort: LoadComputePort
): ComputeUseCase, AllocateUseCase {
    override fun connect(command: ConnectCommand) {
        saveComputePort.saveCompute(
            userId = command.userId,
            computeType = command.computeType,
            maxMemory = command.maxMemory
        )
    }

    override fun disconnect(userId: Long) {
        removeConnectPort.disconnect(userId)
        saveComputePort.deleteCompute(userId)
    }

    override fun allocate(command: AllocateCommand): Long {
        loadComputePort.loadAllCompute().forEach {
            if (command.computeCount <= it.remainCompute && command.useMemory <= it.remainMemory) {
                return it.userId
            }
        }

        throw IllegalArgumentException("할당 가능한 컴퓨터가 없습니다.")
    }
}