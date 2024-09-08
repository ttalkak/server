package com.ttalkak.project.project.domain.model.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProjectEditor {
    private final String projectName;
    private final String domainName;

    @Builder
    public ProjectEditor(String projectName, String domainName) {
        this.projectName = projectName;
        this.domainName = domainName;
    }
}
