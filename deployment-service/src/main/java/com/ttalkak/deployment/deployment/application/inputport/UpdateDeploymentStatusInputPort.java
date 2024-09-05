package com.ttalkak.deployment.deployment.application.inputport;

import com.ttalkak.deployment.deployment.application.outputport.DeploymentOutputPort;
import com.ttalkak.deployment.deployment.application.usecase.UpdateDeploymentStatusUsecase;
import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.domain.model.vo.DeploymentStatus;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentUpdateStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UpdateDeploymentStatusInputPort implements UpdateDeploymentStatusUsecase {

    private final DeploymentOutputPort deploymentOutputPort;

    @Override
    public void updateDeploymentStatus(DeploymentUpdateStatusRequest deploymentUpdateStatusRequest) {
        DeploymentEntity deploymentEntity = deploymentOutputPort.findDeployment(deploymentUpdateStatusRequest.getDeploymentId())
                .orElseThrow(() -> new IllegalArgumentException("해당 배포아이디는 존재하지 않습니다."));
        deploymentEntity.setStatus(DeploymentStatus.valueOf(deploymentUpdateStatusRequest.getStatus()));
        deploymentOutputPort.save(deploymentEntity);
    }
}
