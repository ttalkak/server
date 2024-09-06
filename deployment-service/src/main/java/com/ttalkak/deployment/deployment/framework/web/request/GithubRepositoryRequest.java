package com.ttalkak.deployment.deployment.framework.web.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GithubRepositoryRequest {


    @NotNull(message = "깃허브 레포지토리 이름은 필수입니다.")
    private String repositoryName;


    @NotNull(message = "깃허브 레포지토리 URL은 필수입니다.")
    private String repositoryUrl;


    @NotNull(message = "깃허브 레포지토리 마지막 커밋 메시지는 필수입니다.")
    private String repositoryLastCommitMessage;


    @NotNull(message = "깃허브 레포지토리 커밋 유저 이미지는 필수입니다.")
    private String repositoryLastCommitUserProfile;


    @NotNull(message = "깃허브 레포지토리 마지막 커밋 유저 이름은 필수입니다.")
    private String repositoryLastCommitUserName;


    @NotNull(message = "깃허브 레포지토리 루트 디렉토리는 필수입니다.")
    private String rootDirectory;

    @Builder
    private GithubRepositoryRequest(String repositoryName, String repositoryUrl, String repositoryLastCommitMessage, String repositoryLastCommitUserProfile, String repositoryLastCommitUserName, String rootDirectory) {
        this.repositoryName = repositoryName;
        this.repositoryUrl = repositoryUrl;
        this.repositoryLastCommitMessage = repositoryLastCommitMessage;
        this.repositoryLastCommitUserProfile = repositoryLastCommitUserProfile;
        this.repositoryLastCommitUserName = repositoryLastCommitUserName;
        this.rootDirectory = rootDirectory;
    }
}
