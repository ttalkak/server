package kr.kro.ttalkak.deployment.deployment.framework.jpaadapter;

import kr.kro.ttalkak.deployment.deployment.domain.HostingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostingRepository extends JpaRepository<HostingEntity, Long> {
}
