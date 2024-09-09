package com.ttalkak.deployment.deployment.application.inputport;

import com.ttalkak.deployment.common.ApiResponse;
import com.ttalkak.deployment.deployment.application.outputport.DeploymentOutputPort;
import com.ttalkak.deployment.deployment.application.outputport.GithubOutputPort;
import com.ttalkak.deployment.deployment.application.outputport.ProjectOutputPort;
import com.ttalkak.deployment.deployment.application.usecase.UpdateDeploymentUsecase;
import com.ttalkak.deployment.deployment.domain.model.DatabaseEntity;
import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.domain.model.HostingEntity;
import com.ttalkak.deployment.deployment.domain.model.vo.DatabaseEditor;
import com.ttalkak.deployment.deployment.domain.model.vo.DeploymentEditor;
import com.ttalkak.deployment.deployment.domain.model.vo.GithubInfo;
import com.ttalkak.deployment.deployment.domain.model.vo.ServiceType;
import com.ttalkak.deployment.deployment.framework.projectadapter.dto.ProjectInfoResponse;
import com.ttalkak.deployment.deployment.framework.web.request.DatabaseCreateRequest;
import com.ttalkak.deployment.deployment.framework.web.request.DatabaseUpdateRequest;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentUpdateRequest;
import com.ttalkak.deployment.deployment.framework.web.response.DeploymentResponse;
import com.ttalkak.deployment.global.error.ErrorCode;
import com.ttalkak.deployment.global.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UpdateDeploymentInputPort implements UpdateDeploymentUsecase {

    private final DeploymentOutputPort deploymentOutputPort;

    private final ProjectOutputPort projectOutputPort;

    @Override
    public DeploymentResponse updateDeployment(DeploymentUpdateRequest deploymentUpdateRequest) {

        DeploymentEntity deploymentEntity = deploymentOutputPort.findDeployment(deploymentUpdateRequest.getDeploymentId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXISTS_DEPLOYMENT));

        // 깃허브 관련 정보 객체 생성
        GithubInfo newGithubInfo = GithubInfo.create(
                deploymentUpdateRequest.getGithubRepositoryRequest().getRepositoryName(),
                deploymentUpdateRequest.getGithubRepositoryRequest().getRepositoryUrl(),
                deploymentUpdateRequest.getGithubRepositoryRequest().getRepositoryLastCommitMessage(),
                deploymentUpdateRequest.getGithubRepositoryRequest().getRepositoryLastCommitUserName(),
                deploymentUpdateRequest.getGithubRepositoryRequest().getRepositoryLastCommitUserProfile(),
                deploymentUpdateRequest.getGithubRepositoryRequest().getRootDirectory()
        );

        // 프로젝트의 도메인명 가져오기
        ProjectInfoResponse projectInfo = projectOutputPort.getProjectInfo(deploymentEntity.getProjectId());
        String domainName = projectInfo.getDomainName();

        // 호스팅 정보 수정
        deploymentEntity.getHostingEntities().forEach(hostingEntity -> {
                            // 포트 업데이트
                            hostingEntity.setHostingPort(deploymentUpdateRequest.getHostingUpdateRequest().getHostingPort());
                            // 도메인명 업데이트
                            hostingEntity.updateDomainName(domainName, String.valueOf(deploymentEntity.getServiceType()));
                        });

        // 업데이트된 내용의 데이터베이스
        List<DatabaseUpdateRequest> updatedDatabases = deploymentUpdateRequest.getDatabaseUpdateRequests();

        // 데이터베이스 정보 수정
        deploymentEntity.getDataBaseEntities().forEach(databaseEntity -> {

            Optional.ofNullable(updatedDatabases)
                    .ifPresent(databases -> databases.forEach(updatedDatabase -> {
                        if(databaseEntity.getId().equals(updatedDatabase.getId())) {
                            DatabaseEditor.DatabaseEditorBuilder databaseEditorBuilder = databaseEntity.toEditor();
                            DatabaseEditor databaseEditor = databaseEditorBuilder
                                    .username(updatedDatabase.getUsername())
                                    .password(updatedDatabase.getPassword())
                                    .port(updatedDatabase.getPort())
                                    .build();

                            databaseEntity.edit(databaseEditor);
                        }
                    }));
        });

        // 배포 객체 수정
        DeploymentEditor.DeploymentEditorBuilder deploymentEditorBuilder = deploymentEntity.toEditor();

        DeploymentEditor deploymentEditor = deploymentEditorBuilder.githubInfo(newGithubInfo)
                .env(deploymentUpdateRequest.getEnv())
                .build();

        deploymentEntity.edit(deploymentEditor);

        DeploymentEntity savedDeployment = deploymentOutputPort.save(deploymentEntity);

        // 업데이트 내역 알림 ===============================================


        return DeploymentResponse.mapToDTO(savedDeployment);
    }
}
