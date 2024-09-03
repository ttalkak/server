package com.ttalkak.deployment.deployment.application.usecase;

import com.ttalkak.deployment.deployment.framework.web.request.DeploymentCreateRequest;
import com.ttalkak.deployment.deployment.framework.web.response.DeploymentResponse;

public interface CreateDeploymentUsecase {

    public DeploymentResponse createDeployment(DeploymentCreateRequest deploymentCreateRequest);
}
