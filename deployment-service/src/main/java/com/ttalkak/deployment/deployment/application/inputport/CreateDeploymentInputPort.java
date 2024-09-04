package com.ttalkak.deployment.deployment.application.inputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttalkak.deployment.deployment.application.outputport.*;
import com.ttalkak.deployment.deployment.application.usecase.CreateDeploymentUsecase;
import com.ttalkak.deployment.deployment.domain.event.CreateInstanceEvent;
import com.ttalkak.deployment.deployment.domain.event.DatabaseEvent;
import com.ttalkak.deployment.deployment.domain.event.DeploymentEvent;
import com.ttalkak.deployment.deployment.domain.event.HostingEvent;
import com.ttalkak.deployment.deployment.domain.event.*;
import com.ttalkak.deployment.deployment.domain.model.DatabaseEntity;
import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.domain.model.HostingEntity;
import com.ttalkak.deployment.deployment.domain.model.vo.GithubInfo;
import com.ttalkak.deployment.deployment.domain.model.vo.ServiceType;
import com.ttalkak.deployment.deployment.framework.domainadapter.dto.DomainKeyResponse;
import com.ttalkak.deployment.deployment.framework.projectadapter.dto.ProjectInfoResponse;
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

    private final ProjectOutputPort projectOutputPort;

    private final DomainOutputPort domainOutputPort;

    private final EventOutputPort eventOutputPort;

    @Override
    public DeploymentResponse createDeployment(DeploymentCreateRequest deploymentCreateRequest) {


        // 깃허브 관련 정보 객체 생성
        GithubInfo githubInfo = GithubInfo.create(
                deploymentCreateRequest.getGithubRepositoryRequest().getRepositoryName(),
                deploymentCreateRequest.getGithubRepositoryRequest().getRepositoryUrl(),
                deploymentCreateRequest.getGithubRepositoryRequest().getRepositoryLastCommitMessage(),
                deploymentCreateRequest.getGithubRepositoryRequest().getRepositoryLastCommitUserName(),
                deploymentCreateRequest.getGithubRepositoryRequest().getRepositoryLastCommitUserProfile(),
                deploymentCreateRequest.getGithubRepositoryRequest().getRootDirectory()
        );

       // 배포 객체 생성
        DeploymentEntity deployment = DeploymentEntity.createDeployment(
                deploymentCreateRequest.getProjectId(),
                ServiceType.valueOf(deploymentCreateRequest.getServiceType()),
                githubInfo,
                deploymentCreateRequest.getEnv()
        );
        // 배포 객체 저장
        DeploymentEntity savedDeployment = deploymentOutputPort.save(deployment);


        // OpenFeign을 이용하여 프로젝트서비스로부터 프로젝트정보(도메인 이름)받아오기
        ProjectInfoResponse projectInfo = projectOutputPort.getProjectInfo(deploymentCreateRequest.getProjectId());
        String domainName = projectInfo.getDomainName();




        // 호스팅 객체 생성
        HostingEntity hosting = HostingEntity.createHosting(
                savedDeployment,
                deploymentCreateRequest.getHostingCreateRequest().getHostingPort(),
                deploymentCreateRequest.getServiceType(),
                domainName
        );

        // 호스팅 객체 저장
        HostingEntity savedHostingEntity = hostingOutputPort.save(hosting);
        deployment.addHostingEntity(savedHostingEntity);

        // 서버로 요청해서 도메인 키 받아오기
//        DomainKeyResponse domainKeyResponse = domainOutputPort.makeDomainKey(savedHostingEntity.getId());
//        String detailSubDomainKey = domainKeyResponse.getKey();
        String detailSubDomainKey = null;

        savedHostingEntity.setDetailSubDomainKey(detailSubDomainKey);

        // 백엔드 서버면? =>  데이터베이스가 존재한다.
        List<DatabaseEvent> databaseEvents = null;
        if(ServiceType.isBackendType(deploymentCreateRequest.getServiceType())){

            databaseEvents = new ArrayList<>();
            for(DatabaseCreateRequest databaseCreateRequest : deploymentCreateRequest.getDatabaseCreateRequests()) {
                DatabaseEntity database = DatabaseEntity.createDatabase(
                        savedDeployment,
                        databaseCreateRequest.getDatabasePort(),
                        databaseCreateRequest.getDatabaseName(),
                        databaseCreateRequest.getUsername(),
                        databaseCreateRequest.getPassword()
                );
                DatabaseEntity savedDatabaseEntity = databaseOutputPort.save(database);
                savedDeployment.addDatabaseEntity(savedDatabaseEntity);
                databaseEvents.add(new DatabaseEvent(savedDatabaseEntity.getId(),
                        savedDatabaseEntity.getDatabaseType().toString(),
                        savedDatabaseEntity.getUsername(),
                        savedDatabaseEntity.getPassword(),
                        savedDatabaseEntity.getPort()
                ));
            }
        }

        HostingEvent hostingEvent = new HostingEvent(savedDeployment.getId(), savedHostingEntity.getId(), null, savedHostingEntity.getHostingPort(), null,hosting.getDetailSubDomainName(), hosting.getDetailSubDomainKey());
        DeploymentEvent deploymentEvent = new DeploymentEvent(savedDeployment.getId(), savedDeployment.getProjectId(), savedDeployment.getEnv());
        GithubInfoEvent githubInfoEvent = new GithubInfoEvent(deployment.getGithubInfo().getRepositoryUrl(), deployment.getGithubInfo().getRootDirectory());
        CreateInstanceEvent createInstanceEvent = new CreateInstanceEvent(deploymentEvent, hostingEvent, databaseEvents, githubInfoEvent);
        try {
            eventOutputPort.occurCreateInstance(createInstanceEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("카프카 요청 오류가 발생했습니다.");
        }

        return DeploymentResponse.mapToDTO(savedDeployment);
    }
}
