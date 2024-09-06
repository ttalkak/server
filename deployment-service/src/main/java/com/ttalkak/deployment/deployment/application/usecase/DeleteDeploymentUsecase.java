package com.ttalkak.deployment.deployment.application.usecase;

import com.ttalkak.deployment.deployment.framework.web.request.DeploymentDeleteRequest;

public interface DeleteDeploymentUsecase {
    void deleteDeployment(DeploymentDeleteRequest deploymentDeleteRequest);


    public void deleteDeploymentByProject(Long projectId);
}
