package com.ttalkak.deployment.deployment.framework.web.response;

import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class DeploymentResponse {

    private Long deploymentId;

    private Long projectId;

    // TODO: Enum 으로 변경 (
    //    RUNNING,
    //    STOPPED,
    //    DELETED,
    //    PENDING
    //   )
    private String status;

    // TODO: Enum 으로 변경 (FRONTEND, BACKEND)
    private String serviceType;

    // 상세 조회에는 필요한 정보
    private String repositoryName;
    private String repositoryUrl;

    // 전체 조회 상세 조회 전부 필요함.
    private String branch;
    private String repositoryLastCommitMessage;
    private String repositoryLastCommitUserProfile;
    private String repositoryLastCommitUserName;

    private String framework;

    // 상세 조회에는 필요한 정보
    private List<EnvResponse> envs;

    // 상세 조회에는 필요한 정보
    private List<HostingResponse> hostingResponses;

    // 상세 조회에는 필요한 정보
    private List<DatabaseResponse> databaseResponses;

    @Builder
    private DeploymentResponse(Long deploymentId, Long projectId, String status, String serviceType, String repositoryName, String repositoryUrl, String repositoryLastCommitMessage, String repositoryLastCommitUserProfile, String repositoryLastCommitUserName, List<HostingResponse> hostingResponses, List<EnvResponse> envs, String branch, String framework, List<DatabaseResponse> databaseResponses) {
        this.deploymentId = deploymentId;
        this.projectId = projectId;
        this.status = status;
        this.serviceType = serviceType;
        this.repositoryName = repositoryName;
        this.repositoryUrl = repositoryUrl;
        this.repositoryLastCommitMessage = repositoryLastCommitMessage;
        this.repositoryLastCommitUserProfile = repositoryLastCommitUserProfile;
        this.repositoryLastCommitUserName = repositoryLastCommitUserName;
        this.hostingResponses = hostingResponses;
        this.branch = branch;
        this.envs = envs;
        this.framework = framework;
        this.databaseResponses = databaseResponses;
    }

    public static DeploymentResponse mapToDTO(DeploymentEntity deploymentEntity){
        return DeploymentResponse.builder()
                .deploymentId(deploymentEntity.getId())
                .projectId(deploymentEntity.getProjectId())
                .status(String.valueOf(deploymentEntity.getStatus()))
                .serviceType(String.valueOf(deploymentEntity.getServiceType()))
                .repositoryLastCommitMessage(deploymentEntity.getGithubInfo().getRepositoryLastCommitMessage())
                .repositoryLastCommitUserName(deploymentEntity.getGithubInfo().getRepositoryLastCommitUserName())
                .repositoryLastCommitUserProfile(deploymentEntity.getGithubInfo().getRepositoryLastCommitUserProfile())
                .repositoryUrl(deploymentEntity.getGithubInfo().getRepositoryUrl())
                .repositoryName(deploymentEntity.getGithubInfo().getRepositoryName())
                .hostingResponses(deploymentEntity.getHostingEntities().stream()
                        .map(HostingResponse::mapToDTO)
                        .toList())
                .envs(deploymentEntity.getEnvs().stream()
                        .map(EnvResponse::mapToDTO)
                        .toList())
                .branch(deploymentEntity.getGithubInfo().getBranch())
                .framework(deploymentEntity.getFramework())
                .databaseResponses(deploymentEntity.getDataBaseEntities().stream()
                        .map(DatabaseResponse::mapToDTO)
                        .toList())
                .build();
    }
}
