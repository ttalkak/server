package com.ttalkak.deployment.config;

import com.ttalkak.deployment.deployment.framework.projectadapter.dto.ProjectInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PROJECT-SERVICE")
public interface ProjectFeignClient {

    @GetMapping("/feign/project/{projectId}")
    ProjectInfoResponse getProjectInfo(@PathVariable("projectId")Long projectId);
}
