package com.ttalkak.deployment.deployment.application.outputport;

import com.ttalkak.deployment.deployment.domain.model.VersionEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionOutputPort {

    VersionEntity save(VersionEntity versionEntity);

    VersionEntity findById(Long versionId);
}
