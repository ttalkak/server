package com.ttalkak.deployment.deployment.application.usecase;

import com.ttalkak.deployment.deployment.framework.web.request.DeploymentUpdateRequest;
import com.ttalkak.deployment.deployment.framework.web.response.DeploymentResponse;

public interface UpdateDeploymentUsecase {
    DeploymentResponse updateDeployment(DeploymentUpdateRequest deploymentUpdateRequest);
}
