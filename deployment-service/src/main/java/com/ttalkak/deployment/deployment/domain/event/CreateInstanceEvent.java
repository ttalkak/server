package com.ttalkak.deployment.deployment.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInstanceEvent implements Serializable {

    private DeploymentEvent deploymentEvent;

    private HostingEvent hostingEvent;

    private List<DatabaseEvent> databaseEvents;


}