package com.ttalkak.project.config;

import com.ttalkak.project.common.ApiResponse;
import com.ttalkak.project.project.framework.deploymentadapter.dto.DeploymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "DEPLOYMENT-SERVICE")
public interface DeploymentFeignClient {

    @DeleteMapping("/v1/deployment/{projectId}")
    void deleteDeployment(@PathVariable("projectId")Long projectId);

    @GetMapping("/v1/deployment/feign/project/{projectId}")
    List<DeploymentResponse> getDeployments(@PathVariable("projectId")Long projectId);

}
