package kr.kro.ttalkak.deployment.deployment.framework.jpaadapter;

import kr.kro.ttalkak.deployment.deployment.application.outputport.DeploymentOutputPort;
import kr.kro.ttalkak.deployment.deployment.domain.DeploymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class DeploymentAdapter implements DeploymentOutputPort {

    private final DeploymentRepository deploymentRepository;

    @Override
    public List<DeploymentEntity> findAllByProjectId(Long projectId) {
        return deploymentRepository.findAllByProjectId(projectId);
    }

    @Override
    public List<DeploymentEntity> searchDeploymentByGithubRepoName(String githubRepoName, int page, int size) {
        PageRequest paging = PageRequest.of(page, size);
        return deploymentRepository.searchDeploymentByGithubRepoName(githubRepoName, (Pageable) paging);
    }

    @Override
    public Optional<DeploymentEntity> findDeployment(Long deploymentId) {
        return deploymentRepository.findById(deploymentId);
    }

    @Override
    public DeploymentEntity save(DeploymentEntity deploymentEntity) {
        return deploymentRepository.save(deploymentEntity);
    }

    @Override
    public void delete(DeploymentEntity deploymentEntity) {
        deploymentRepository.delete(deploymentEntity);
    }
}
