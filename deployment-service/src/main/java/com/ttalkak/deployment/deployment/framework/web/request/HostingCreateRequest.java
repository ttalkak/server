package com.ttalkak.deployment.deployment.framework.web.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class HostingCreateRequest {

    private int hostingPort;


    @Builder
    private HostingCreateRequest(int hostingPort) {
        this.hostingPort = hostingPort;
    }
}
