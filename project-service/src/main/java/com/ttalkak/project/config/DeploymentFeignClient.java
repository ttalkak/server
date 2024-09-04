package com.ttalkak.project.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPLOYMENT-SERVICE")
public interface DeploymentFeignClient {

    @DeleteMapping("/v1/deployment/{projectId}")
    DeploymentFeignClient deleteDeployment(@PathVariable("projectId")Long projectId);
}
