package com.ttalkak.deployment.deployment.framework.jpaadapter.repository;

import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeploymentRepository extends JpaRepository<DeploymentEntity, Long> {

    @Query("select d from DeploymentEntity d where d.projectId = :projectId")
    public List<DeploymentEntity> findAllByProjectId(@Param("projectId") Long projectId);

    @Query("select d from DeploymentEntity d where d.githubInfo.repositoryName like %:githubRepoName% ORDER BY d.id")
    public List<DeploymentEntity> searchDeploymentByGithubRepoName(@Param("githubRepoName") String githubRepoName, Pageable pageable);

}