package com.ttalkak.deployment.deployment.framework.web.request;

import lombok.Getter;

@Getter
public class DeploymentUpdateStatusRequest {

    private Long deploymentId;

    private String status;
}
