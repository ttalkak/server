package com.ttalkak.deployment.deployment.application.outputport;

import com.ttalkak.deployment.deployment.framework.domainadapter.dto.DomainKeyResponse;

public interface DomainOutputPort {

    public DomainKeyResponse makeDomainKey(Long hostingId);
}
