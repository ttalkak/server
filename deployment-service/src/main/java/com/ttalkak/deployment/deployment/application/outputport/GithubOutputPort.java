package com.ttalkak.deployment.deployment.application.outputport;

import com.ttalkak.deployment.deployment.domain.model.vo.GithubCommit;
import com.ttalkak.deployment.deployment.domain.model.vo.GithubRepository;

public interface GithubOutputPort {

    public GithubRepository getRepositoryDetails(String owner, String repo);

    public GithubCommit getLastCommitDetails(String owner, String repo);

}
