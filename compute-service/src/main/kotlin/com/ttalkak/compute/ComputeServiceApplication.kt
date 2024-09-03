package com.ttalkak.compute

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class ComputeServiceApplication

fun main(args: Array<String>) {
    runApplication<ComputeServiceApplication>(*args)
}
