package com.ttalkak.compute.compute.adapter.`in`.web

import com.ttalkak.compute.common.WebAdapter
import com.ttalkak.compute.compute.adapter.`in`.web.request.CreateStatusRequest
import com.ttalkak.compute.compute.domain.ApiResponse
import com.ttalkak.compute.compute.application.port.out.StatusCommand
import com.ttalkak.compute.compute.application.service.StatusService
import com.ttalkak.compute.compute.domain.UserStatus
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
@RequestMapping("/v1/compute")
class ComputeController (
    private val statusService: StatusService,
) {
    @PostMapping("/status")
    @ResponseStatus(HttpStatus.CREATED)
    fun upsertStatus(
        @RequestHeader("X-USER-ID") userId: Long,
        @RequestBody request: CreateStatusRequest

    ): ApiResponse<Any> {
        val command = StatusCommand(
            maxCompute = request.maxCompute,
            availablePortStart = request.availablePortStart,
            availablePortEnd = request.availablePortEnd
        )

        statusService.upsertStatus(userId, command)

        return ApiResponse.success()
    }

    @GetMapping("/status")
    fun getStatus(
        @RequestHeader("X-USER-ID") userId: Long
    ): ApiResponse<UserStatus> {
        val status = statusService.getStatus(userId)

        return ApiResponse.success(status)
    }
}