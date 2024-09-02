package kr.kro.ttalkak.deployment.deployment.application.inputport;

import kr.kro.ttalkak.deployment.deployment.application.outputport.DeploymentOutputPort;
import kr.kro.ttalkak.deployment.deployment.application.outputport.GithubOutputPort;
import kr.kro.ttalkak.deployment.deployment.application.usecase.UpdateDeploymentUsecase;
import kr.kro.ttalkak.deployment.deployment.domain.DeploymentEntity;
import kr.kro.ttalkak.deployment.deployment.domain.vo.GithubCommit;
import kr.kro.ttalkak.deployment.deployment.domain.vo.GithubRepository;
import kr.kro.ttalkak.deployment.deployment.domain.vo.ServiceType;
import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentOutputDTO;
import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentUpdateInputDTO;
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
    public DeploymentOutputDTO updateDeployment(DeploymentUpdateInputDTO deploymentUpdateInputDTO) {

        DeploymentEntity deploymentEntity = deploymentOutputPort.findDeployment(deploymentUpdateInputDTO.getDeploymentId())
                .orElseThrow(() -> new IllegalArgumentException("해당 배포아이디는 존재하지 않습니다."));

        deploymentEntity.setProjectId(deploymentUpdateInputDTO.getDeploymentId());
        deploymentEntity.setServiceType(ServiceType.valueOf(deploymentUpdateInputDTO.getServiceType()));
        // 레포지토리를 바꿨으면?
        if(!deploymentUpdateInputDTO.getGithubRepo().equals(deploymentEntity.getGithubRepository().getRepositoryName())){

            GithubRepository repositoryDetails = githubOutputPort.getRepositoryDetails(deploymentUpdateInputDTO.getGithubOwner(), deploymentUpdateInputDTO.getGithubRepo());
            GithubCommit lastCommitDetails = githubOutputPort.getLastCommitDetails(deploymentUpdateInputDTO.getGithubOwner(), deploymentUpdateInputDTO.getGithubRepo());
            deploymentEntity.setGithubRepository(repositoryDetails);
            deploymentEntity.setGithubCommit(lastCommitDetails);
        }

        deploymentOutputPort.save(deploymentEntity);
        return DeploymentOutputDTO.mapToDTO(deploymentEntity);
    }
}
