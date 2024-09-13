package com.ttalkak.deployment.deployment.application.inputport;

import com.ttalkak.deployment.deployment.application.outputport.DeploymentOutputPort;
import com.ttalkak.deployment.deployment.application.outputport.HostingOutputPort;
import com.ttalkak.deployment.deployment.application.usecase.UpdateHostingUsecase;
import com.ttalkak.deployment.deployment.domain.event.HostingEvent;
import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.domain.model.HostingEntity;
import com.ttalkak.deployment.common.global.error.ErrorCode;
import com.ttalkak.deployment.common.global.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateHostingInputPort implements UpdateHostingUsecase {

    private final HostingOutputPort hostingOutputPort;

    private final DeploymentOutputPort deploymentOutputPort;

    @Override
    public void updateHosting(HostingEvent hostingEvent) {
        HostingEntity findHosting = hostingOutputPort.findById(hostingEvent.getHostingId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXISTS_HOSTING));

        findHosting.setDeployerId(hostingEvent.getDeployerId());
        hostingOutputPort.save(findHosting);
    }

    @Override
    public void updateHostingDomainName(Long projectId, String newDomainName) {
    }
}
