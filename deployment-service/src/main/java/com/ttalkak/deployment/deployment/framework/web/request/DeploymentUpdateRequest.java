package com.ttalkak.deployment.deployment.framework.web.request;

import lombok.Getter;

@Getter
public class DeploymentUpdateRequest {

    private Long deploymentId;

    private Long projectId;

    private String serviceType;

    private GithubRepositoryRequest githubRepositoryRequest;
}
