package kr.kro.ttalkak.deployment.deployment.adapter.out.persistence.repository;

import kr.kro.ttalkak.deployment.deployment.adapter.out.persistence.entity.DeploymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeploymentJpaRepository extends JpaRepository<DeploymentEntity,Long> {
}
