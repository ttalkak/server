package kr.kro.ttalkak.deployment.deployment.application.inputport;

import kr.kro.ttalkak.deployment.deployment.application.outputport.DeploymentOutputPort;
import kr.kro.ttalkak.deployment.deployment.application.usecase.InquiryUsecase;
import kr.kro.ttalkak.deployment.deployment.domain.DeploymentEntity;
import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentOutputDTO;
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
    public DeploymentOutputDTO getDeployment(Long deploymentId) {
        DeploymentEntity deploymentEntity = deploymentOutputPort.findDeployment(deploymentId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 배포내역이 존재하지 않습니다."));
        return DeploymentOutputDTO.mapToDTO(deploymentEntity);
    }

    // 프로젝트 관련 배포이력 전체조회
    @Override
    public List<DeploymentOutputDTO> getDeploymentsByProjectId(Long projectId) {
        return deploymentOutputPort.findAllByProjectId(projectId)
                .stream()
                .map(DeploymentOutputDTO::mapToDTO)
                .collect(Collectors.toList());
    }

    // 레포지토리 이름을 포함하면 반

    @Override
    public List<DeploymentOutputDTO> searchDeploymentByGithubRepositoryName(String githubRepoName, int page, int size) {
        return deploymentOutputPort.searchDeploymentByGithubRepoName(githubRepoName, page, size)
                .stream()
                .map(DeploymentOutputDTO::mapToDTO)
                .collect(Collectors.toList());
    }
}
