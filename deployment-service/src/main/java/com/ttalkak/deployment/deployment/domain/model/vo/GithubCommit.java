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
public class GithubCommit {

    @Column(nullable = false)
    private String repositoryLastCommitMessage;

    @Column(nullable = false)
    private String repositoryLastCommitUserProfile;

    @Column(nullable = false)
    private String repositoryLastCommitUserName;
}
