package com.ttalkak.deployment.deployment.framework.web.request;

import com.ttalkak.deployment.deployment.domain.model.DatabaseEntity;
import com.ttalkak.deployment.deployment.framework.web.response.HostingResponse;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeploymentUpdateRequest {

    @NotNull(message = "배포 아이디는 필수입니다.")
    private Long deploymentId;;

    private GithubRepositoryRequest githubRepositoryRequest;

    private List<DatabaseUpdateRequest> databaseUpdateRequests;

    private HostingUpdateRequest hostingUpdateRequest;

    private String env;

    @Builder
    private DeploymentUpdateRequest(Long deploymentId, GithubRepositoryRequest githubRepositoryRequest,
                                    List<DatabaseUpdateRequest> databaseUpdateRequests, HostingUpdateRequest hostingUpdateRequest, String env) {
        this.deploymentId = deploymentId;
        this.deploymentId = 0L;
        this.githubRepositoryRequest = githubRepositoryRequest;
        this.databaseUpdateRequests = databaseUpdateRequests;
        this.hostingUpdateRequest = hostingUpdateRequest;
        this.env = env;
    }

}
