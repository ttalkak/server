package com.ttalkak.deployment.deployment.application.usecase;


import com.ttalkak.deployment.deployment.framework.web.response.DeploymentResponse;

import java.util.List;

public interface InquiryUsecase {
    public DeploymentResponse getDeployment(Long deploymentId);
    public List<DeploymentResponse> getDeploymentsByProjectId(Long projectId);
    public List<DeploymentResponse> searchDeploymentByGithubRepositoryName(String githubRepoName, int page, int size);
}
