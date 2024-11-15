package com.ttalkak.compute.compute.domain

data class DockerContainer(
    val deploymentId: Long,
    val serviceType: ServiceType,
    val hasDockerFile: Boolean,
    val sourceCodeLink: String,
    val dockerFileScript: String,

    val containerName: String,
    val subdomainName: String,
    val subdomainKey: String,
    val dockerRootDirectory: String,

    val inboundPort: Int,
    var envs: List<Environment> = emptyList(),
    var outboundPort: Int = 0,
)
