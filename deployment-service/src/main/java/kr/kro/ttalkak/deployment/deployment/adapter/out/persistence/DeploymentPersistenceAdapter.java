package kr.kro.ttalkak.deployment.deployment.adapter.out.persistence;

import kr.kro.ttalkak.deployment.common.PersistenceAdapter;
import kr.kro.ttalkak.deployment.deployment.adapter.out.persistence.entity.DeploymentEntity;
import kr.kro.ttalkak.deployment.deployment.adapter.out.persistence.repository.DeploymentJpaRepository;
import kr.kro.ttalkak.deployment.deployment.application.port.out.CreatePort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class DeploymentPersistenceAdapter implements CreatePort {

    private final DeploymentJpaRepository deploymentJpaRepository;

    public void create(Long projectId){
        DeploymentEntity entity = new DeploymentEntity(projectId, true, "testUrl.com");
        deploymentJpaRepository.save(entity);
    }

}
