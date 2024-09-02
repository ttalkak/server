package kr.kro.ttalkak.deployment.deployment.application.usecase;

import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentCreateInputDTO;
import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentOutputDTO;

public interface CreateDeploymentUsecase {

    public DeploymentOutputDTO createDeployment(DeploymentCreateInputDTO deploymentCreateInputDTO);
}
