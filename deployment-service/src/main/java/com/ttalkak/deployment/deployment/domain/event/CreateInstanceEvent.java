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


    private String deploymentId;

    private String env;

    private String hostingId;

    private String hostingPort;

    private String deployerId;

    private String projectId;

    private String detailSubDomainName;

    private String detailSubDomainKey;

    private String serviceType;


    private String repositoryUrl;

    private String rootDirectory;

    private List<DatabaseEvent> database;

    public CreateInstanceEvent(DeploymentEvent deployment, HostingEvent hosting, GithubInfoEvent githubInfo, List<DatabaseEvent> database) {
        this.deploymentId = deployment.getDeploymentId().toString();
        this.env = deployment.getEnv();
        this.hostingId = hosting.getHostingId().toString();
        this.hostingPort = String.valueOf(hosting.getHostingPort());
        this.deployerId = String.valueOf(hosting.getDeploymentId());
        this.projectId = String.valueOf(deployment.getProjectId());
        this.detailSubDomainName = hosting.getSubdomainName();
        this.detailSubDomainKey = hosting.getDetailSubDomainKey();
        this.serviceType = deployment.getServiceType();
        this.repositoryUrl = githubInfo.getRepositoryUrl();
        this.rootDirectory = githubInfo.getRepositoryUrl();
        this.database = database;
    }


}
