package kr.kro.ttalkak.deployment.deployment.framework.web.dto;

import kr.kro.ttalkak.deployment.deployment.domain.DeploymentEntity;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class DeploymentOutputDTO {

    private Long deploymentId;

    private Long projectId;

    private String status;

    private String serviceType;

    private String repositoryName;

    private String repositoryUrl;

    private String repositoryLastCommitMessage;

    private String repositoryLastCommitUserProfile;

    private String repositoryLastCommitUserName;

    @Builder
    private DeploymentOutputDTO(Long deploymentId, Long projectId, String status, String serviceType, String repositoryName, String repositoryUrl, String repositoryLastCommitMessage, String repositoryLastCommitUserProfile, String repositoryLastCommitUserName) {
        this.deploymentId = deploymentId;
        this.projectId = projectId;
        this.status = status;
        this.serviceType = serviceType;
        this.repositoryName = repositoryName;
        this.repositoryUrl = repositoryUrl;
        this.repositoryLastCommitMessage = repositoryLastCommitMessage;
        this.repositoryLastCommitUserProfile = repositoryLastCommitUserProfile;
        this.repositoryLastCommitUserName = repositoryLastCommitUserName;
    }

    public static DeploymentOutputDTO mapToDTO(DeploymentEntity deploymentEntity){
        return DeploymentOutputDTO.builder()
                .deploymentId(deploymentEntity.getId())
                .projectId(deploymentEntity.getProjectId())
                .status(String.valueOf(deploymentEntity.getStatus()))
                .serviceType(String.valueOf(deploymentEntity.getServiceType()))
                .repositoryLastCommitMessage(deploymentEntity.getGithubCommit().getRepositoryLastCommitMessage())
                .repositoryLastCommitUserName(deploymentEntity.getGithubCommit().getRepositoryLastCommitUserName())
                .repositoryLastCommitUserProfile(deploymentEntity.getGithubCommit().getRepositoryLastCommitUserProfile())
                .repositoryUrl(deploymentEntity.getGithubRepository().getRepositoryUrl())
                .repositoryName(deploymentEntity.getGithubRepository().getRepositoryName())
                .build();
    }

}
