package com.ttalkak.deployment.deployment.framework.web.request;

import lombok.Getter;

import java.util.List;

@Getter
public class DeploymentCreateRequest {

    private Long projectId;

    private String serviceType;

    private String githubOwner;

    private String githubRepo;

    private String rootDirectory;

    private List<DatabaseCreateRequest> databaseCreateDTOs;

    private HostingCreateRequest hostingCreateRequest;

    private String env;
}
