package kr.kro.ttalkak.deployment.deployment.application.service;

import kr.kro.ttalkak.deployment.common.UseCase;
import kr.kro.ttalkak.deployment.deployment.application.port.in.DeploymentCreateCommand;
import kr.kro.ttalkak.deployment.deployment.application.port.in.DeploymentCreateUseCase;
import kr.kro.ttalkak.deployment.deployment.application.port.out.CreatePort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeploymentCreateService implements DeploymentCreateUseCase {

    private final CreatePort createPort;

    @Override
    public void create(DeploymentCreateCommand command) {
        createPort.create(command.getProjectId());
    }
}
