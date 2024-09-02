package kr.kro.ttalkak.deployment.deployment.application.usecase;

import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentOutputDTO;
import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentUpdateInputDTO;

public interface UpdateDeploymentUsecase {
    DeploymentOutputDTO updateDeployment(DeploymentUpdateInputDTO deploymentUpdateInputDTO);
}
