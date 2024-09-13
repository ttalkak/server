package com.ttalkak.deployment.deployment.framework.jpaadapter.repository;

import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.domain.model.VersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionRepository extends JpaRepository<VersionEntity, Long> {

    @Query("SELECT v FROM VersionEntity v where v.id = :id ORDER BY v.version")
    public VersionEntity findLastVersionById(long id);

    @Query("SELECT v FROM VersionEntity v where v.deploymentEntity = :deploymentId ORDER BY v.id DESC")
    public List<VersionEntity> findAllByDeploymentId(DeploymentEntity deploymentEntity);
}