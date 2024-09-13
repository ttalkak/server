package com.ttalkak.project.project.framework.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectUpdateRequest {
    String projectName;
    String domainName;

    @Builder
    public ProjectUpdateRequest(String projectName, String domainName) {
        this.projectName = projectName;
        this.domainName = domainName;
    }
}
