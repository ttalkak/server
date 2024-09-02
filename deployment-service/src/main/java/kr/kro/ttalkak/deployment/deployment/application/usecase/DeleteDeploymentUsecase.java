package kr.kro.ttalkak.deployment.deployment.application.usecase;

import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentDeleteInputDTO;

public interface DeleteDeploymentUsecase {
    void deleteDeployment(DeploymentDeleteInputDTO deploymentDeleteInputDTO);
}
