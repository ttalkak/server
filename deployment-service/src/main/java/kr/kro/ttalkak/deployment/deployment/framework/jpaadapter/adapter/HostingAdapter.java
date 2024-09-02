package kr.kro.ttalkak.deployment.deployment.framework.jpaadapter;

import kr.kro.ttalkak.deployment.deployment.application.outputport.HostingOutputPort;
import kr.kro.ttalkak.deployment.deployment.domain.HostingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HostingAdapter implements HostingOutputPort {

    private final HostingRepository hostingRepository;

    @Override
    public HostingEntity save(HostingEntity hostingEntity) {
        HostingEntity savedHostingEntity = hostingRepository.save(hostingEntity);
        return savedHostingEntity;
    }
}
