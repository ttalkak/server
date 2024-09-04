package com.ttalkak.project.project.framework.deploymentadapter;

import com.ttalkak.project.config.DeploymentFeignClient;
import com.ttalkak.project.project.application.outputport.DeploymentOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeploymentAdapter implements DeploymentOutputPort {

    private final DeploymentFeignClient deploymentFeignClient;

    @Override
    public void deleteDeployment(Long projectId) {
        deploymentFeignClient.deleteDeployment(projectId);
    }
}
