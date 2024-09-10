package com.ttalkak.deployment.deployment.framework.web.request;

import lombok.Getter;

@Getter
public class DeploymentCommandStatusRequest {

    private String deploymentId;

    private String command;
}
