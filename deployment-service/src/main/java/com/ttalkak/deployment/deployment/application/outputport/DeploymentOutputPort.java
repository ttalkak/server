package com.ttalkak.deployment.deployment.application.outputport;

import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.domain.model.vo.DeploymentStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeploymentOutputPort {

    public List<DeploymentEntity> findAllByProjectId(Long projectId);

    public List<DeploymentEntity> searchDeploymentByGithubRepoName(String githubRepoName, int page, int size);

    public Optional<DeploymentEntity> findDeployment(Long deploymentId);

    public DeploymentEntity save(DeploymentEntity deploymentEntity);

    public void delete(DeploymentEntity deploymentEntity);
}
