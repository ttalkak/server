package kr.kro.ttalkak.deployment.deployment.application.outputport;

import kr.kro.ttalkak.deployment.deployment.domain.HostingEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface HostingOutputPort {

    public HostingEntity save(HostingEntity hostingEntity);
}
