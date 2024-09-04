package com.ttalkak.deployment.config;

import com.ttalkak.deployment.deployment.framework.projectadapter.dto.ProjectInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "projectServiceFeignClient", url = "https://localhost:8000/v1/project")
public interface ProjectFeignClient {

    @GetMapping("/{projectId}")
    ProjectInfoResponse getProjectInfo(@PathVariable("projectId")Long projectId);
}
