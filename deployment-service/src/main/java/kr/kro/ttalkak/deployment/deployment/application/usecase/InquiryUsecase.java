package kr.kro.ttalkak.deployment.deployment.application.usecase;


import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentOutputDTO;

import java.util.List;

public interface InquiryUsecase {
    public DeploymentOutputDTO getDeployment(Long deploymentId);
    public List<DeploymentOutputDTO> getDeploymentsByProjectId(Long projectId);
    public List<DeploymentOutputDTO> searchDeploymentByGithubRepositoryName(String githubRepoName, int page, int size);
}
