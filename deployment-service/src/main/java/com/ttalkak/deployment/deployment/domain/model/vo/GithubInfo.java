package com.ttalkak.deployment.deployment.domain.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class GithubInfo {

    private String repositoryName;

    private String repositoryUrl;

    @Column(nullable = false)
    private String rootDirectory;

    @Column(nullable = false)
    private String branch;

    public static GithubInfo create(String repositoryName, String repositoryUrl, String rootDirectory, String branch) {
        GithubInfo githubInfo = new GithubInfo();
        githubInfo.repositoryName = repositoryName;
        githubInfo.repositoryUrl = repositoryUrl;
        githubInfo.rootDirectory = rootDirectory;
        githubInfo.branch = branch;
        return githubInfo;
    }
}
