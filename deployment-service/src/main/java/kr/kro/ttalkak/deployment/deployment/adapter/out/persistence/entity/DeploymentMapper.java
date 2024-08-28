package kr.kro.ttalkak.deployment.deployment.adapter.out.persistence.entity;

import kr.kro.ttalkak.deployment.deployment.domain.Deployment;

public class DeploymentMapper {

    public static Deployment toDomain(DeploymentEntity deploymentEntity){
        Deployment deployment = new Deployment();
        deployment.setStatus(deploymentEntity.isStatus());
        deployment.setUrl(deploymentEntity.getUrl());
        deployment.setProjectId(deploymentEntity.getProjectId());
        return deployment;
    }
}
