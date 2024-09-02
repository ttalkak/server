package kr.kro.ttalkak.deployment.deployment.framework.web.dto;

import lombok.Getter;

@Getter
public class DeploymentUpdateInputDTO {

    private Long deploymentId;

    private Long projectId;

    private String serviceType;

    private String githubOwner;

    private String githubRepo;
}
