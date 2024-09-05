package com.ttalkak.compute.compute.adapter.out.persistence.repository

import com.ttalkak.compute.compute.adapter.out.persistence.entity.StatusEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StatusRepository: JpaRepository<StatusEntity, Long> {
}