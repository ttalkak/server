package kr.kro.ttalkak.deployment.deployment.application.outputport;

import kr.kro.ttalkak.deployment.deployment.domain.vo.GithubCommit;
import kr.kro.ttalkak.deployment.deployment.domain.vo.GithubRepository;

public interface GithubOutputPort {

    public GithubRepository getRepositoryDetails(String owner, String repo);

    public GithubCommit getLastCommitDetails(String owner, String repo);

}
