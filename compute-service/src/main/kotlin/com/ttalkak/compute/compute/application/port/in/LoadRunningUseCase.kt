package com.ttalkak.compute.compute.application.port.`in`

import com.ttalkak.compute.compute.domain.ComputeRunning
import com.ttalkak.compute.compute.domain.ServiceType
import java.util.Optional

interface LoadRunningUseCase {
    fun loadRunning(id: Long, serviceType: ServiceType): Optional<ComputeRunning>
}