package com.ttalkak.deployment.deployment.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentEvent {

    private Long deploymentId;

    private Long projectId;

    private String env;

    private String serviceType;
}
