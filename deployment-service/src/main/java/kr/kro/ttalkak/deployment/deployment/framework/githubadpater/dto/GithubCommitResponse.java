package kr.kro.ttalkak.deployment.deployment.framework.githubadpater.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Getter;


@Getter
public class GithubCommitResponse {

    @JsonProperty("commit.message")
    private String repositoryLastCommitMessage;


    @JsonProperty("committer.avatar_url")
    private String repositoryLastCommitUserProfile;


    @JsonProperty("committer.avatar_login")
    private String repositoryLastCommitUserName;
}
