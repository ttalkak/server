package com.ttalkak.deployment.deployment.application.inputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttalkak.deployment.common.global.error.ErrorCode;
import com.ttalkak.deployment.common.global.exception.BusinessException;
import com.ttalkak.deployment.common.global.exception.EntityNotFoundException;
import com.ttalkak.deployment.deployment.application.outputport.*;
import com.ttalkak.deployment.deployment.application.usecase.DeleteDatabaseUsecase;
import com.ttalkak.deployment.deployment.application.usecase.DeleteDeploymentUsecase;
import com.ttalkak.deployment.deployment.domain.event.CommandEvent;
import com.ttalkak.deployment.deployment.domain.event.UpdateDeploymentStatusEvent;
import com.ttalkak.deployment.deployment.domain.model.DatabaseEntity;
import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.domain.model.HostingEntity;
import com.ttalkak.deployment.deployment.domain.model.vo.DeploymentStatus;
import com.ttalkak.deployment.deployment.framework.kafka.ChangeStatusProducer;
import com.ttalkak.deployment.deployment.framework.projectadapter.dto.ProjectInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeleteDatabaseInputPort implements DeleteDatabaseUsecase {

    private final DatabaseOutputPort databaseOutputPort;

    @Override
    public void deleteDatabase(Long userId, Long databaseId) {
        DatabaseEntity databaseEntity = databaseOutputPort.findById(databaseId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXISTS_DATABASE));

        if(!databaseEntity.getUserId().equals(userId)){
            throw new BusinessException(ErrorCode.UN_AUTHORIZATION);
        }

        databaseOutputPort.delete(databaseEntity);
    }
}
