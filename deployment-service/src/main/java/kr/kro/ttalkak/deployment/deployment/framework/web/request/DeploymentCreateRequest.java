package kr.kro.ttalkak.deployment.deployment.framework.web.dto;

import lombok.Getter;

@Getter
public class DeploymentCreateInputDTO {

    private Long projectId;

    private String serviceType;

    private String githubOwner;

    private String githubRepo;

    private DatabaseCreateInputDTO databaseCreateDTO;

    private HostingCreateInputDTO hostingCreateInputDTO;
}
