package com.ttalkak.deployment.deployment.framework.jpaadapter.adapter;

import com.ttalkak.deployment.common.global.error.ErrorCode;
import com.ttalkak.deployment.common.global.exception.BusinessException;
import com.ttalkak.deployment.deployment.application.outputport.VersionOutputPort;
import com.ttalkak.deployment.deployment.domain.model.VersionEntity;
import com.ttalkak.deployment.deployment.framework.jpaadapter.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VersionAdapter implements VersionOutputPort {

    private final VersionRepository versionRepository;

    @Override
    public VersionEntity save(VersionEntity versionEntity) {
        return versionRepository.save(versionEntity);
    }


    @Override
    public VersionEntity findById(Long versionId) {
        VersionEntity versionEntity = versionRepository.findLastVersionById(versionId);
        return versionEntity;
    }
}
