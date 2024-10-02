package com.ttalkak.compute.compute.domain

data class Database(
    val databaseId: Long,
    val databaseType: DatabaseType,
    val name: String,
    val username: String,
    val password: String,
)
