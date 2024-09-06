package com.ttalkak.deployment.deployment.framework.web.request;

import lombok.Getter;

@Getter
public class DeploymentUpdateStatusRequest {

    private String deploymentId;

    private String status;
}
