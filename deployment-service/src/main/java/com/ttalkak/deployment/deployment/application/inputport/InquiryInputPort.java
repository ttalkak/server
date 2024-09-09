package com.ttalkak.deployment.deployment.application.inputport;

import com.ttalkak.deployment.deployment.application.outputport.DeploymentOutputPort;
import com.ttalkak.deployment.deployment.application.usecase.InquiryUsecase;
import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.domain.model.vo.DeploymentStatus;
import com.ttalkak.deployment.deployment.framework.web.response.DeploymentResponse;
import com.ttalkak.deployment.global.error.ErrorCode;
import com.ttalkak.deployment.global.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryInputPort implements InquiryUsecase {

    private final DeploymentOutputPort deploymentOutputPort;


    // 배포이력 상세조회
    @Override
    public DeploymentResponse getDeployment(Long deploymentId) {
        DeploymentEntity deploymentEntity = deploymentOutputPort.findDeployment(deploymentId)
                // 배포 이력이 존재하지 않은 경우
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXISTS_DEPLOYMENT));
        return DeploymentResponse.mapToDTO(deploymentEntity);
    }

    // 프로젝트 관련 배포이력 전체조회
    @Override
    public List<DeploymentResponse> getDeploymentsByProjectId(Long projectId) {
        return deploymentOutputPort.findAllByProjectId(projectId)
                .stream()
                .filter(deployment -> DeploymentStatus.isAlive(deployment.getStatus()))
                .map(DeploymentResponse::mapToDTO)
                .collect(Collectors.toList());
    }

    // 레포지토리 이름을 포함하면 반
    @Override
    public List<DeploymentResponse> searchDeploymentByGithubRepositoryName(String githubRepoName, int page, int size) {
        return deploymentOutputPort.searchDeploymentByGithubRepoName(githubRepoName, page, size)
                .stream()
                .filter(deployment -> DeploymentStatus.isAlive(deployment.getStatus()))
                .map(DeploymentResponse::mapToDTO)
                .collect(Collectors.toList());
    }
}
