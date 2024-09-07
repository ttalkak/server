package com.ttalkak.compute.compute.adapter.`in`.web

import com.ttalkak.compute.common.WebAdapter
import com.ttalkak.compute.compute.domain.ApiResponse
import com.ttalkak.compute.compute.application.port.`in`.StatusCommand
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
    @GetMapping("/status")
    fun getStatus(
        @RequestHeader("X-USER-ID") userId: Long
    ): ApiResponse<UserStatus> {
        val status = statusService.getStatus(userId)

        return ApiResponse.success(status)
    }
}