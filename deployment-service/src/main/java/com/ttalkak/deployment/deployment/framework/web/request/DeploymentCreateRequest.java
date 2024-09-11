package com.ttalkak.deployment.deployment.framework.web.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class DeploymentCreateRequest {

    @NotNull(message = "프로젝트 아이디는 필수입니다.")
    private Long projectId;

    @NotNull(message = "서비스타입은 필수입니다.")
    private String serviceType;

    @NotNull(message = "호스팅 포트는 필수입니다.")
    private int hostingPort;

    private GithubRepositoryRequest githubRepositoryRequest;

    @Nullable
    private List<DatabaseCreateRequest> databaseCreateRequests;

    private List<EnvCreateRequest> envs = new ArrayList<>();

    private String framework;

    @Builder
    private DeploymentCreateRequest(Long projectId, String serviceType, GithubRepositoryRequest githubRepositoryRequest, List<DatabaseCreateRequest> databaseCreateRequests, int hostingPort, List<EnvCreateRequest> envs, String framework) {
        this.projectId = projectId;
        this.serviceType = serviceType;
        this.githubRepositoryRequest = githubRepositoryRequest;
        this.databaseCreateRequests = databaseCreateRequests;
        this.hostingPort = hostingPort;
        this.envs = envs;
        this.framework = framework;
    }
}
