package com.ttalkak.compute.compute.application.service

import com.ttalkak.compute.common.UseCase
import com.ttalkak.compute.compute.application.port.`in`.*
import com.ttalkak.compute.compute.application.port.out.LoadComputePort
import com.ttalkak.compute.compute.application.port.out.RemoveConnectPort
import com.ttalkak.compute.compute.application.port.out.SaveComputePort
import com.ttalkak.compute.compute.application.port.out.SaveDeploymentStatusPort

@UseCase
class ComputeService (
    private val saveComputePort: SaveComputePort,
    private val removeConnectPort: RemoveConnectPort,
    private val loadComputePort: LoadComputePort,
    private val saveDeploymentStatusPort: SaveDeploymentStatusPort
): ComputeUseCase, AllocateUseCase, ComputeStatusUseCase {
    override fun connect(command: ConnectCommand) {
        saveComputePort.saveCompute(
            userId = command.userId,
            computeType = command.computeType,
            usedCompute = command.usedCompute,
            usedMemory = command.usedMemory,
            usedCPU = command.usedCPU
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

    override fun update(connectCommand: ConnectCommand, deploymentCommands: List<DeploymentCommand>) {
        connect(connectCommand)

        deploymentCommands.forEach {
            saveDeploymentStatusPort.saveDeploymentStatus(
                userId = connectCommand.userId,
                deploymentId = it.deploymentId,
                status = it.status.toString(),
                useMemory = it.useMemory,
                useCPU = it.useCPU,
                runningTime = it.runningTime,
                diskRead = it.diskRead,
                diskWrite = it.diskWrite
            )
        }
    }
}