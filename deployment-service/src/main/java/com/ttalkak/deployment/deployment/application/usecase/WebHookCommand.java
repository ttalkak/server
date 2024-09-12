package com.ttalkak.deployment.deployment.application.usecase;

import lombok.Data;

@Data
public class WebHookCommand {
    private Long projectId;
    private String repositoryName;
    private String repositoryUrl;
    private String branch;
    private String commitUsername;
    private String commitUserProfile;
    private String commitMessage;
}
