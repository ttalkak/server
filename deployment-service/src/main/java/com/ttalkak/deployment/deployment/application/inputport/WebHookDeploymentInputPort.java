package com.ttalkak.deployment.deployment.application.inputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttalkak.deployment.common.UseCase;
import com.ttalkak.deployment.deployment.application.outputport.DeploymentOutputPort;
import com.ttalkak.deployment.deployment.application.outputport.EventOutputPort;
import com.ttalkak.deployment.deployment.application.outputport.HostingOutputPort;
import com.ttalkak.deployment.deployment.application.outputport.ProjectOutputPort;
import com.ttalkak.deployment.deployment.application.usecase.WebHookCommand;
import com.ttalkak.deployment.deployment.application.usecase.WebHookDeploymentUsecase;
import com.ttalkak.deployment.deployment.domain.event.*;
import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.domain.model.HostingEntity;
import com.ttalkak.deployment.deployment.domain.model.vo.GithubInfo;
import com.ttalkak.deployment.deployment.domain.model.vo.ServiceType;
import com.ttalkak.deployment.deployment.framework.projectadapter.dto.ProjectWebHookResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class WebHookDeploymentInputPort implements WebHookDeploymentUsecase {
    private final ProjectOutputPort projectOutputPort;
    private final DeploymentOutputPort deploymentOutputPort;
    private final HostingOutputPort hostingOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public void createDeploymentWebHook(ServiceType serviceType, String webhookToken, WebHookCommand command) {
        ProjectWebHookResponse response = projectOutputPort.getWebHookProject(webhookToken);

        // TODO: 에러 처리 추가
        DeploymentEntity deployment = deploymentOutputPort.findByProjectIdAndServiceType(response.getProjectId(), serviceType.name()).orElseThrow(
                () -> new IllegalArgumentException("올바르지 않은 배포 정보입니다.")
        );

        HostingEntity hosting = hostingOutputPort.findByProjectIdAndServiceType(response.getProjectId(), serviceType.name());

        // 기존 DB 와 최신 정보를 통해 GithubInfo 정보 신규 생성
        GithubInfo githubInfo = GithubInfo.create(
                command.getRepositoryName(),
                command.getRepositoryUrl(),
                command.getCommitMessage(),
                command.getCommitUsername(),
                command.getCommitUserProfile(),
                deployment.getGithubInfo().getRootDirectory(),
                deployment.getGithubInfo().getBranch()
        );

        DeploymentEntity newDeployment = DeploymentEntity.createDeployment(
                response.getProjectId(),
                serviceType,
                githubInfo,
                deployment.getFramework()
        );

        List<EnvEvent> envEvents = deployment.getEnvs().stream().map(env -> new EnvEvent(env.getKey(), env.getValue())).toList();
        HostingEvent hostingEvent = new HostingEvent(
                newDeployment.getId(), hosting.getId(),
                "", hosting.getHostingPort(),
                hosting.getDeployerId(),
                hosting.getDetailSubDomainName(),
                hosting.getDetailSubDomainKey()
        );
        DeploymentEvent deploymentEvent = new DeploymentEvent(newDeployment.getId(), newDeployment.getProjectId(), envEvents, newDeployment.getServiceType().toString());
        GithubInfoEvent githubInfoEvent = new GithubInfoEvent(deployment.getGithubInfo().getRepositoryUrl(), deployment.getGithubInfo().getRootDirectory(), newDeployment.getGithubInfo().getBranch());
        CreateInstanceEvent createInstanceEvent = new CreateInstanceEvent(deploymentEvent, hostingEvent, githubInfoEvent, envEvents, null);

        try {
            eventOutputPort.occurRebuildInstance(createInstanceEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("카프카 요청 오류가 발생했습니다.");
        }
    }
}
