package com.ttalkak.deployment.deployment.application.inputport;

import com.ttalkak.deployment.deployment.application.outputport.DeploymentOutputPort;
import com.ttalkak.deployment.deployment.application.usecase.DeleteDeploymentUsecase;
import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentDeleteRequest;
import com.ttalkak.deployment.common.global.error.ErrorCode;
import com.ttalkak.deployment.common.global.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class DeleteDeploymentInputPort implements DeleteDeploymentUsecase {

    private final DeploymentOutputPort deploymentOutputPort;

    @Override
    public void deleteDeployment(Long deploymentId) {
        DeploymentEntity deploymentEntity = deploymentOutputPort.findDeployment(deploymentId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXISTS_DEPLOYMENT));
        deploymentEntity.deleteDeployment();
        deploymentOutputPort.save(deploymentEntity);
    }

    @Override
    public void deleteDeploymentByProject(Long projectId) throws Exception {
        List<DeploymentEntity> deploymentEntities = deploymentOutputPort.findAllByProjectId(projectId);
        for(DeploymentEntity deploymentEntity : deploymentEntities) {
            deploymentEntity.deleteDeployment();
        }
        deploymentOutputPort.saveAll(deploymentEntities);

    }
}
