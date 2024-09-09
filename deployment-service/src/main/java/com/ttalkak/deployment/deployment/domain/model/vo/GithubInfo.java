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

    @Column(nullable = false)
    private String repositoryLastCommitMessage;

    @Column(nullable = false)
    private String repositoryLastCommitUserProfile;

    @Column(nullable = false)
    private String repositoryLastCommitUserName;

    @Column(nullable = false)
    private String repositoryName;

    @Column(nullable = false)
    private String repositoryUrl;

    @Column(nullable = false)
    private String rootDirectory;

    @Column(nullable = false)
    private String branch;

    public static GithubInfo create(String repositoryName, String repositoryUrl, String repositoryLastCommitMessage, String repositoryLastCommitUserName, String repositoryLastCommitUserProfile, String rootDirectory, String branch) {
        GithubInfo githubInfo = new GithubInfo();
        githubInfo.repositoryLastCommitMessage = repositoryLastCommitMessage;
        githubInfo.repositoryLastCommitUserProfile = repositoryLastCommitUserProfile;
        githubInfo.repositoryLastCommitUserName = repositoryLastCommitUserName;
        githubInfo.repositoryName = repositoryName;
        githubInfo.repositoryUrl = repositoryUrl;
        githubInfo.rootDirectory = rootDirectory;
        githubInfo.branch = branch;
        return githubInfo;
    }
}
