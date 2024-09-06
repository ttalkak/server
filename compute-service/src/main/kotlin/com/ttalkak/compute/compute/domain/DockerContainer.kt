package com.ttalkak.compute.compute.domain

data class DockerContainer(
    private val hasDockerImage: Boolean,
    private val containerName: String,
    private val port: Int,
    private val subdomainName: String,
    private val subdomainKey: String,

    private val sourceCodeLink: String?,
    private val dockerRootDirectory: String?,

    private val dockerImageName: String?,
    private val dockerImageTag: String?,
)
