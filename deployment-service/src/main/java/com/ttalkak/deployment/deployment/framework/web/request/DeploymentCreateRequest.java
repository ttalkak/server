package com.ttalkak.deployment.deployment.framework.web.request;

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
}
