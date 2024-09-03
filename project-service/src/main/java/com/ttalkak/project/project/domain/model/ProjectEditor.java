package com.ttalkak.project.project.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProjectEditor {
    String projectName;

    @Builder
    public ProjectEditor(String projectName) {
        this.projectName = projectName;
    }
}
