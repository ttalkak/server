package com.ttalkak.deployment.deployment.framework.web.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeploymentCreateRequest {

    private Long projectId;

    private String serviceType;

    private GithubRepositoryRequest githubRepositoryRequest;

    private List<DatabaseCreateRequest> databaseCreateRequests;

    private HostingCreateRequest hostingCreateRequest;

    private String env;

    @Builder
    private DeploymentCreateRequest(Long projectId, String serviceType, GithubRepositoryRequest githubRepositoryRequest, List<DatabaseCreateRequest> databaseCreateRequests, HostingCreateRequest hostingCreateRequest, String env) {
        this.projectId = projectId;
        this.serviceType = serviceType;
        this.githubRepositoryRequest = githubRepositoryRequest;
        this.databaseCreateRequests = databaseCreateRequests;
        this.hostingCreateRequest = hostingCreateRequest;
        this.env = env;
    }
}
