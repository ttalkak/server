package com.ttalkak.deployment.deployment.framework.web.request;

import lombok.Getter;

@Getter
public class HostingCreateRequest {

    private int hostingPort;

    private String hostingIp;

    private Long domainId;

    private Long deployerId;
}
