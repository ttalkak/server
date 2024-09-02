package com.ttalkak.deployment.deployment.application.inputport;

import com.ttalkak.deployment.deployment.application.outputport.DeploymentOutputPort;
import com.ttalkak.deployment.deployment.application.outputport.GithubOutputPort;
import com.ttalkak.deployment.deployment.application.usecase.UpdateDeploymentUsecase;
import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.domain.model.vo.GithubCommit;
import com.ttalkak.deployment.deployment.domain.model.vo.GithubRepository;
import com.ttalkak.deployment.deployment.domain.model.vo.ServiceType;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentUpdateRequest;
import com.ttalkak.deployment.deployment.framework.web.response.DeploymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UpdateDeploymentInputPort implements UpdateDeploymentUsecase {

    private final DeploymentOutputPort deploymentOutputPort;

    private final GithubOutputPort githubOutputPort;

    @Override
    public DeploymentResponse updateDeployment(DeploymentUpdateRequest deploymentUpdateRequest) {

        DeploymentEntity deploymentEntity = deploymentOutputPort.findDeployment(deploymentUpdateRequest.getDeploymentId())
                .orElseThrow(() -> new IllegalArgumentException("해당 배포아이디는 존재하지 않습니다."));

        deploymentEntity.setProjectId(deploymentUpdateRequest.getDeploymentId());
        deploymentEntity.setServiceType(ServiceType.valueOf(deploymentUpdateRequest.getServiceType()));
        // 레포지토리를 바꿨으면?
        if(!deploymentUpdateRequest.getGithubRepo().equals(deploymentEntity.getGithubRepository().getRepositoryName())){

            GithubRepository repositoryDetails = githubOutputPort.getRepositoryDetails(deploymentUpdateRequest.getGithubOwner(), deploymentUpdateRequest.getGithubRepo());
            GithubCommit lastCommitDetails = githubOutputPort.getLastCommitDetails(deploymentUpdateRequest.getGithubOwner(), deploymentUpdateRequest.getGithubRepo());
            deploymentEntity.setGithubRepository(repositoryDetails);
            deploymentEntity.setGithubCommit(lastCommitDetails);
        }

        deploymentOutputPort.save(deploymentEntity);
        return DeploymentResponse.mapToDTO(deploymentEntity);
    }
}
