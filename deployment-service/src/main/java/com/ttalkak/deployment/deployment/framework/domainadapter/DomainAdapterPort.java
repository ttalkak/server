package com.ttalkak.deployment.deployment.framework.domainadapter;

import com.ttalkak.deployment.config.DomainFeignClient;
import com.ttalkak.deployment.deployment.application.outputport.DomainOutputPort;
import com.ttalkak.deployment.deployment.framework.domainadapter.dto.DomainKeyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainAdapterPort implements DomainOutputPort {

    private final DomainFeignClient domainFeignClient;

    @Override
    public DomainKeyResponse makeDomainKey(Long hostingId) {
        return domainFeignClient.getDomainKey(hostingId);

    }
}
