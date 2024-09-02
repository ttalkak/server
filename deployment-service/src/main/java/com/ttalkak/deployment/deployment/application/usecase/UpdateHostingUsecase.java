package com.ttalkak.deployment.deployment.application.usecase;

import com.ttalkak.deployment.deployment.domain.event.HostingEvent;

public interface UpdateHostingUsecase {
    void updateHosting(HostingEvent hostingEvent);
}
