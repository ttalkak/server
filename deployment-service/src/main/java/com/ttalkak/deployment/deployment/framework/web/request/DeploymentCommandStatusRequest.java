package com.ttalkak.deployment.deployment.framework.web.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeploymentCommandStatusRequest {

    private Long deploymentId;

    private String command;

    @Builder
    public DeploymentCommandStatusRequest(Long deploymentId, String command) {
        this.deploymentId = deploymentId;
        this.command = command;
    }


}
