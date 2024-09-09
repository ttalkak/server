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

    private String port;

    private String subdomainName;

    private String subdomainKey;

    private String serviceType;

    private String branch;

    private String repositoryUrl;

    private String rootDirectory;

    private List<DatabaseEvent> databases;

    public CreateInstanceEvent(DeploymentEvent deployment, HostingEvent hosting, GithubInfoEvent githubInfo, List<DatabaseEvent> database) {
        this.deploymentId = deployment.getDeploymentId().toString();
        this.env = deployment.getEnv();
        this.port = String.valueOf(hosting.getHostingPort());
        this.subdomainName = hosting.getSubdomainName();
        this.subdomainKey = hosting.getSubdomainKey();
        this.serviceType = deployment.getServiceType();
        this.branch = githubInfo.getBranch();
        this.repositoryUrl = githubInfo.getRepositoryUrl();
        this.rootDirectory = githubInfo.getRootDirectory();
        this.databases = database;
    }


}
