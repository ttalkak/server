package com.ttalkak.deployment.deployment.framework.web.response;

import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.domain.model.vo.DeploymentStatus;
import com.ttalkak.deployment.deployment.domain.model.vo.ServiceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class DeploymentPreviewResponse {

    private Long deploymentId;

    private Long projectId;

    @Enumerated(EnumType.STRING)
    private DeploymentStatus status;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    private String branch;

    private String repositoryLastCommitMessage;

    private String repositoryLastCommitUserProfile;

    private String repositoryLastCommitUserName;

    private String framework;

    @Builder
    private DeploymentPreviewResponse(Long deploymentId, Long projectId, DeploymentStatus status, ServiceType serviceType, String repositoryLastCommitMessage, String repositoryLastCommitUserProfile, String repositoryLastCommitUserName,  String branch, String framework) {
        this.deploymentId = deploymentId;
        this.projectId = projectId;
        this.status = status;
        this.serviceType = serviceType;
        this.repositoryLastCommitMessage = repositoryLastCommitMessage;
        this.repositoryLastCommitUserProfile = repositoryLastCommitUserProfile;
        this.repositoryLastCommitUserName = repositoryLastCommitUserName;
        this.branch = branch;
        this.framework = framework;
    }

    public static DeploymentPreviewResponse mapToDTO(DeploymentEntity deploymentEntity){
        return DeploymentPreviewResponse.builder()
                .deploymentId(deploymentEntity.getId())
                .projectId(deploymentEntity.getProjectId())
                .status(deploymentEntity.getStatus())
                .serviceType(deploymentEntity.getServiceType())
                .repositoryLastCommitMessage(deploymentEntity.getGithubInfo().getRepositoryLastCommitMessage())
                .repositoryLastCommitUserName(deploymentEntity.getGithubInfo().getRepositoryLastCommitUserName())
                .repositoryLastCommitUserProfile(deploymentEntity.getGithubInfo().getRepositoryLastCommitUserProfile())
                .branch(deploymentEntity.getGithubInfo().getBranch())
                .framework(deploymentEntity.getFramework())
                .build();
    }
}
