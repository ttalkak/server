package kr.kro.ttalkak.deployment.deployment.framework.web.dto;

import lombok.Getter;

@Getter
public class HostingCreateInputDTO {

    private int hostingPort;

    private String hostingIp;

    private Long domainId;

    private Long deployerId;
}
