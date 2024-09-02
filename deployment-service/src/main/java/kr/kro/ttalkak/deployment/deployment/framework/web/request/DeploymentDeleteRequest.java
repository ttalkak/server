package kr.kro.ttalkak.deployment.deployment.framework.web.dto;

import lombok.Getter;

@Getter
public class DeploymentDeleteInputDTO {

    private Long deploymentId;

    private String serviceType;

    private String githubOwner;

    private String githubRepo;
}
