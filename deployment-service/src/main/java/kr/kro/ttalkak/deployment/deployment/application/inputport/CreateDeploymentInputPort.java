package kr.kro.ttalkak.deployment.deployment.application.inputport;

import kr.kro.ttalkak.deployment.deployment.application.outputport.DatabaseOutputPort;
import kr.kro.ttalkak.deployment.deployment.application.outputport.DeploymentOutputPort;
import kr.kro.ttalkak.deployment.deployment.application.outputport.GithubOutputPort;
import kr.kro.ttalkak.deployment.deployment.application.outputport.HostingOutputPort;
import kr.kro.ttalkak.deployment.deployment.application.usecase.CreateDeploymentUsecase;
import kr.kro.ttalkak.deployment.deployment.domain.DatabaseEntity;
import kr.kro.ttalkak.deployment.deployment.domain.DeploymentEntity;
import kr.kro.ttalkak.deployment.deployment.domain.HostingEntity;
import kr.kro.ttalkak.deployment.deployment.domain.vo.GithubCommit;
import kr.kro.ttalkak.deployment.deployment.domain.vo.GithubRepository;
import kr.kro.ttalkak.deployment.deployment.domain.vo.ServiceType;
import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentCreateInputDTO;
import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateDeploymentInputPort implements CreateDeploymentUsecase {

    private final DeploymentOutputPort deploymentOutputPort;

    private final HostingOutputPort hostingOutputPort;

    private final DatabaseOutputPort databaseOutputPort;

    private final GithubOutputPort githubOutputPort;

    @Override
    public DeploymentOutputDTO createDeployment(DeploymentCreateInputDTO deploymentCreateInputDTO) {


        GithubRepository repositoryDetails = githubOutputPort.getRepositoryDetails(deploymentCreateInputDTO.getGithubOwner(), deploymentCreateInputDTO.getGithubRepo());
        GithubCommit lastCommitDetails = githubOutputPort.getLastCommitDetails(deploymentCreateInputDTO.getGithubOwner(), deploymentCreateInputDTO.getGithubRepo());
        // 배포 객체 생성
        DeploymentEntity deployment = DeploymentEntity.createDeployment(
                deploymentCreateInputDTO.getProjectId(),
                ServiceType.valueOf(deploymentCreateInputDTO.getServiceType()),
                lastCommitDetails,
                repositoryDetails
        );
        // 배포 객체 저장
        DeploymentEntity saveDeployment = deploymentOutputPort.save(deployment);


        HostingEntity hosting = HostingEntity.createHosting(
                saveDeployment,
                deploymentCreateInputDTO.getHostingCreateInputDTO().getHostingPort(),
                deploymentCreateInputDTO.getHostingCreateInputDTO().getDeployerId(),
                deploymentCreateInputDTO.getHostingCreateInputDTO().getHostingIp(),
                deploymentCreateInputDTO.getHostingCreateInputDTO().getDomainId(),
                deploymentCreateInputDTO.getServiceType()
        );
        hostingOutputPort.save(hosting);


        // 백엔드 서버면? =>  데이터베이스가 존재한다.
        if(ServiceType.isBackendType(deploymentCreateInputDTO.getServiceType())){
            DatabaseEntity database = DatabaseEntity.createDatabase(
                    saveDeployment,
                    deploymentCreateInputDTO.getDatabaseCreateDTO().getDatabasePort(),
                    deploymentCreateInputDTO.getDatabaseCreateDTO().getDatabaseName(),
                    deploymentCreateInputDTO.getDatabaseCreateDTO().getUsername(),
                    deploymentCreateInputDTO.getDatabaseCreateDTO().getPassword()
            );
            databaseOutputPort.save(database);
        }

        return DeploymentOutputDTO.mapToDTO(saveDeployment);
    }
}
