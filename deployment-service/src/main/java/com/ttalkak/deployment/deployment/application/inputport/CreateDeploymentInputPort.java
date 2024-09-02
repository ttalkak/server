package com.ttalkak.deployment.deployment.application.inputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttalkak.deployment.deployment.application.outputport.*;
import com.ttalkak.deployment.deployment.application.usecase.CreateDeploymentUsecase;
import com.ttalkak.deployment.deployment.domain.event.CreateInstanceEvent;
import com.ttalkak.deployment.deployment.domain.event.DatabaseEvent;
import com.ttalkak.deployment.deployment.domain.event.DeploymentEvent;
import com.ttalkak.deployment.deployment.domain.event.HostingEvent;
import com.ttalkak.deployment.deployment.domain.model.DatabaseEntity;
import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.domain.model.HostingEntity;
import com.ttalkak.deployment.deployment.domain.model.vo.GithubCommit;
import com.ttalkak.deployment.deployment.domain.model.vo.GithubRepository;
import com.ttalkak.deployment.deployment.domain.model.vo.ServiceType;
import com.ttalkak.deployment.deployment.framework.web.request.DatabaseCreateRequest;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentCreateRequest;
import com.ttalkak.deployment.deployment.framework.web.response.DeploymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateDeploymentInputPort implements CreateDeploymentUsecase {

    private final DeploymentOutputPort deploymentOutputPort;

    private final HostingOutputPort hostingOutputPort;

    private final DatabaseOutputPort databaseOutputPort;

    private final GithubOutputPort githubOutputPort;

    private final EventOutputPort eventOutputPort;

    @Override
    public DeploymentResponse createDeployment(DeploymentCreateRequest deploymentCreateRequest) {


        GithubRepository repositoryDetails = githubOutputPort.getRepositoryDetails(deploymentCreateRequest.getGithubOwner(), deploymentCreateRequest.getGithubRepo());
        GithubCommit lastCommitDetails = githubOutputPort.getLastCommitDetails(deploymentCreateRequest.getGithubOwner(), deploymentCreateRequest.getGithubRepo());
        // 배포 객체 생성
        DeploymentEntity deployment = DeploymentEntity.createDeployment(
                deploymentCreateRequest.getProjectId(),
                ServiceType.valueOf(deploymentCreateRequest.getServiceType()),
                lastCommitDetails,
                repositoryDetails,
                deploymentCreateRequest.getRootDirectory(),
                deploymentCreateRequest.getEnv()
        );
        // 배포 객체 저장
        DeploymentEntity savedDeployment = deploymentOutputPort.save(deployment);



        HostingEntity hosting = HostingEntity.createHosting(
                savedDeployment,
                deploymentCreateRequest.getHostingCreateRequest().getHostingPort(),
                deploymentCreateRequest.getHostingCreateRequest().getDomainId(),
                deploymentCreateRequest.getServiceType()
        );
        HostingEntity saveHostingEntity = hostingOutputPort.save(hosting);
        deployment.addHostingEntity(saveHostingEntity);


        // 백엔드 서버면? =>  데이터베이스가 존재한다.

        List<DatabaseEvent> databaseEvents = null;
        if(ServiceType.isBackendType(deploymentCreateRequest.getServiceType())){

            databaseEvents = new ArrayList<>();
            for(DatabaseCreateRequest databaseCreateRequest : deploymentCreateRequest.getDatabaseCreateDTOs()) {
                DatabaseEntity database = DatabaseEntity.createDatabase(
                        savedDeployment,
                        databaseCreateRequest.getDatabasePort(),
                        databaseCreateRequest.getDatabaseName(),
                        databaseCreateRequest.getUsername(),
                        databaseCreateRequest.getPassword()
                );
                DatabaseEntity savedDatabaseEntity = databaseOutputPort.save(database);
                deployment.addDatabaseEntity(savedDatabaseEntity);
                databaseEvents.add(new DatabaseEvent(savedDatabaseEntity.getId(),
                        savedDatabaseEntity.getDatabaseType().toString(),
                        savedDatabaseEntity.getUsername(),
                        savedDatabaseEntity.getPassword(),
                        savedDatabaseEntity.getPort()
                ));
            }
        }

        HostingEvent hostingEvent = new HostingEvent(savedDeployment.getId(), saveHostingEntity.getId(), null, saveHostingEntity.getHostingPort(), hosting.getDomainId(), null);
        DeploymentEvent deploymentEvent = new DeploymentEvent(savedDeployment.getId(), savedDeployment.getProjectId(), savedDeployment.getRootDirectory(), savedDeployment.getEnv());
        CreateInstanceEvent createInstanceEvent = new CreateInstanceEvent(deploymentEvent, hostingEvent, databaseEvents);
        try {
            eventOutputPort.occurCreateInstance(createInstanceEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("카프카 요청 오류가 발생했습니다.");
        }

        return DeploymentResponse.mapToDTO(savedDeployment);
    }
}
