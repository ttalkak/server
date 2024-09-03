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

    private DeploymentEvent deployment;

    private HostingEvent hosting;

    private List<DatabaseEvent> database;

    private GithubInfoEvent githubInfo;


}
