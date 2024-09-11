package com.ttalkak.deployment.deployment.application.inputport;

import com.ttalkak.deployment.deployment.application.outputport.DeploymentOutputPort;
import com.ttalkak.deployment.deployment.application.outputport.ProjectOutputPort;
import com.ttalkak.deployment.deployment.application.usecase.DeleteDeploymentUsecase;
import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.framework.projectadapter.dto.ProjectInfoResponse;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentDeleteRequest;
import com.ttalkak.deployment.common.global.error.ErrorCode;
import com.ttalkak.deployment.common.global.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
@Transactional
@RequiredArgsConstructor
public class DeleteDeploymentInputPort implements DeleteDeploymentUsecase {
    private final DeploymentOutputPort deploymentOutputPort;
    private final ProjectOutputPort projectOutputPort;

    @Override
    public void deleteDeployment(Long userId, Long deploymentId) {
        DeploymentEntity deploymentEntity = deploymentOutputPort.findDeployment(deploymentId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXISTS_DEPLOYMENT));
        Long projectId = deploymentEntity.getProjectId();

        ProjectInfoResponse projectInfo = projectOutputPort.getProjectInfo(projectId);

        if(!Objects.equals(userId, projectInfo.getUserId())){
            throw new EntityNotFoundException(ErrorCode.UN_AUTHORIZATION);
        }

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
