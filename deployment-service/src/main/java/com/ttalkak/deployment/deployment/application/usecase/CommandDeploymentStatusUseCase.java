package com.ttalkak.deployment.deployment.application.usecase;

import com.ttalkak.deployment.common.UseCase;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentCommandStatusRequest;

public interface CommandDeploymentStatusUseCase {

    void commandDeploymentStatus(DeploymentCommandStatusRequest deploymentCommandStatusRequest);
}
