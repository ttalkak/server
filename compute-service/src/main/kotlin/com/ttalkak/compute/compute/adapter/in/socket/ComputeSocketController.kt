package com.ttalkak.compute.compute.adapter.`in`.socket

import com.ttalkak.compute.common.SocketAdapter
import com.ttalkak.compute.compute.adapter.`in`.socket.request.ComputeRunningRequest
import com.ttalkak.compute.compute.adapter.`in`.socket.request.ComputeStatusRequest
import com.ttalkak.compute.compute.application.port.`in`.*
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Controller

@SocketAdapter
@Controller
class ComputeSocketController(
    private val computeUseCase: ComputeUseCase,
    private val computeStatusUseCase: ComputeStatusUseCase,
    private val upsertRunningUseCase: UpsertRunningUseCase,
) {
    @MessageMapping("/compute/connect")
    fun compute(@Payload request: ComputeStatusRequest) {
        val command = ConnectCommand(
            userId = request.userId,
            computeType = request.computerType,
            usedCompute = request.usedCompute,
            usedMemory = request.usedMemory,
            usedCPU = request.usedCPU,
        )

        computeUseCase.connect(command)
    }

    /**
     * Compute 상태를 확인하는 ping 요청을 처리
     *
     * @param request Compute 상태 요청
     */
    @MessageMapping("/compute/ping")
    fun ping(@Payload request: ComputeStatusRequest) {
        val command = ConnectCommand(
            userId = request.userId,
            computeType = request.computerType,
            usedCompute = request.usedCompute,
            usedMemory = request.usedMemory,
            usedCPU = request.usedCPU,
        )

        val deploymentCommands = request.deployments.map {
            DeploymentCommand(
                deploymentId = it.deploymentId,
                status = it.status,
                useMemory = it.useMemory,
                useCPU = it.useCPU,
                runningTime = it.runningTime,
                diskRead = it.diskRead,
                diskWrite = it.diskWrite,
            )
        }

        computeStatusUseCase.update(command, deploymentCommands)
    }

    /**
     * 신규 인스턴스 생성 요청에 대한 응답을 처리
     *
     * @param request Compute 상태 요청
     */
    @MessageMapping("/compute/{deploymentId}/status")
    fun status(
        @DestinationVariable deploymentId: Long,
        @Header("X-USER-ID") userId: Long,
        @Payload request: ComputeRunningRequest
    ) {
        val command = RunningCommand(
            deploymentId = deploymentId,
            status = request.status,
            message = request.message
        )

        upsertRunningUseCase.upsertRunning(userId, command)
    }
}