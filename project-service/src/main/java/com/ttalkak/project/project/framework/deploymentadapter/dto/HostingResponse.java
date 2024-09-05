package com.ttalkak.project.project.framework.deploymentadapter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HostingResponse {

    private Long hostingId;

    private String detailDomainName;

    private String serviceType;

    private int hostingPort;
}
