package com.ttalkak.project.project.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProjectEditor {
    String projectName;
    String domainName;

    @Builder
    public ProjectEditor(String projectName, String domainName) {
        this.projectName = projectName;
        this.domainName = domainName;
    }
}
