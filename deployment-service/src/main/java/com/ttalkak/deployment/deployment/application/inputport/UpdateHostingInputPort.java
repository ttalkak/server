package com.ttalkak.deployment.deployment.application.inputport;

import com.ttalkak.deployment.deployment.application.outputport.HostingOutputPort;
import com.ttalkak.deployment.deployment.application.usecase.UpdateHostingUsecase;
import com.ttalkak.deployment.deployment.domain.event.HostingEvent;
import com.ttalkak.deployment.deployment.domain.model.HostingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateHostingInputPort implements UpdateHostingUsecase {

    private final HostingOutputPort hostingOutputPort;

    @Override
    public void updateHosting(HostingEvent hostingEvent) {
        HostingEntity findHosting = hostingOutputPort.findById(hostingEvent.getHostingId())
                .orElseThrow(() -> new IllegalArgumentException("호스팅 객체를 찾을 수 없습니다."));

        findHosting.setDeployerId(hostingEvent.getDeployerId());
        hostingOutputPort.save(findHosting);
    }
}
