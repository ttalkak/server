package com.ttalkak.deployment.deployment.framework.jpaadapter.repository;

import com.ttalkak.deployment.deployment.domain.model.HostingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostingRepository extends JpaRepository<HostingEntity, Long> {
}
