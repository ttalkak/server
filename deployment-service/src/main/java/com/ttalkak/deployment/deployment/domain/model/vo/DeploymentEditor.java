package com.ttalkak.deployment.deployment.domain.model.vo;


import com.ttalkak.deployment.deployment.domain.model.DatabaseEntity;
import com.ttalkak.deployment.deployment.domain.model.HostingEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DeploymentEditor {

    GithubInfo githubInfo;

    String env;

    @Builder
    public DeploymentEditor(GithubInfo githubInfo, String env) {
        this.githubInfo = githubInfo;
        this.env = env;
    }
}
