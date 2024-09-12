package com.ttalkak.deployment.deployment.framework.web;

import com.ttalkak.deployment.common.WebAdapter;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentWebHookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/webhook/deployment")
public class DeploymentWebHookController {
    @PostMapping("/backend/{webhookToken}")
    public void deploymentWebHook(
            @PathVariable String webhookToken,
            @RequestBody DeploymentWebHookRequest deploymentWebHookRequest
    ){
    }

    @PostMapping("/frontend/{webhookToken}")
    public void deploymentWebHookFrontend(
            @PathVariable String webhookToken,
            @RequestBody DeploymentWebHookRequest deploymentWebHookRequest
    ){

    }
}
