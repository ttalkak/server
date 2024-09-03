package com.ttalkak.deployment.deployment.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HostingEvent implements Serializable {

    private Long deploymentId;

    private Long hostingId;

    private String hostingIp;

    private int hostingPort;

    private Long domainId;

    private Long deployerId;

}
