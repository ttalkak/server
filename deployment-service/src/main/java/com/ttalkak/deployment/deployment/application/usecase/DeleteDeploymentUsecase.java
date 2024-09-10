package com.ttalkak.deployment.deployment.application.usecase;

import com.ttalkak.deployment.deployment.framework.web.request.DeploymentDeleteRequest;

public interface DeleteDeploymentUsecase {
    void deleteDeployment(Long deploymentId);


    public void deleteDeploymentByProject(Long projectId) throws Exception;
}
