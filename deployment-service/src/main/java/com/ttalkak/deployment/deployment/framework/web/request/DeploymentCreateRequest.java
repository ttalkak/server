package com.ttalkak.deployment.deployment.framework.web.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeploymentCreateRequest {

    @NotNull(message = "프로젝트 아이디는 필수입니다.")
    private Long projectId;


    @NotNull(message = "서비스타입은 필수입니다.")
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
