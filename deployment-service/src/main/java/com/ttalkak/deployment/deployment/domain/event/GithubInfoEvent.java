package com.ttalkak.deployment.deployment.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GithubInfoEvent {

    private String repositoryUrl;

    private String rootDirectory;

    private String branch;

    public GithubInfoEvent(String repositoryUrl, String rootDirectory) {
        this.repositoryUrl = repositoryUrl;
        this.rootDirectory = rootDirectory;
    }
}
