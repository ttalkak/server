package com.ttalkak.deployment.deployment.application.inputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttalkak.deployment.deployment.application.outputport.DeploymentOutputPort;
import com.ttalkak.deployment.deployment.application.usecase.UpdateDeploymentStatusUsecase;
import com.ttalkak.deployment.deployment.domain.event.UpdateDeploymentStatusEvent;
import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.domain.model.vo.DeploymentStatus;
import com.ttalkak.deployment.deployment.framework.kafka.ChangeStatusProducer;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentUpdateStatusRequest;
import com.ttalkak.deployment.global.error.ErrorCode;
import com.ttalkak.deployment.global.exception.BusinessException;
import com.ttalkak.deployment.global.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UpdateDeploymentStatusInputPort implements UpdateDeploymentStatusUsecase {

    private final DeploymentOutputPort deploymentOutputPort;

    private final ChangeStatusProducer changeStatusProducer;

    @Override
    public void updateDeploymentStatus(DeploymentUpdateStatusRequest deploymentUpdateStatusRequest){
        DeploymentEntity deploymentEntity = deploymentOutputPort.findDeployment(Long.valueOf(deploymentUpdateStatusRequest.getDeploymentId()))
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXISTS_DEPLOYMENT));
        if(deploymentUpdateStatusRequest.getUpdateFrom().equals("web")) {
            deploymentEntity.setStatus(DeploymentStatus.PENDING);
            UpdateDeploymentStatusEvent updateDeploymentStatusEvent = toKafkaEventMessage(deploymentUpdateStatusRequest);
            try{
                changeStatusProducer.occurUpdateDeploymentStatus(updateDeploymentStatusEvent);
            }catch (JsonProcessingException e){
                throw new BusinessException(ErrorCode.KAFKA_PRODUCER_ERROR);
            }
        }

        // Compute Service에서 상태변경하려고하는 경우
        if(deploymentUpdateStatusRequest.getUpdateFrom().equals("compute")){
            deploymentEntity.setStatus(DeploymentStatus.valueOf(deploymentUpdateStatusRequest.getStatus()));
        }
        deploymentOutputPort.save(deploymentEntity);
    }

    private UpdateDeploymentStatusEvent toKafkaEventMessage(DeploymentUpdateStatusRequest deploymentUpdateStatusRequest) {
        UpdateDeploymentStatusEvent updateDeploymentStatusEvent = new UpdateDeploymentStatusEvent(
                deploymentUpdateStatusRequest.getDeploymentId(),
                DeploymentStatus.PENDING.toString(),
                deploymentUpdateStatusRequest.getUpdateFrom(),
                deploymentUpdateStatusRequest.getCommand()
                );
        return updateDeploymentStatusEvent;
    }
}
