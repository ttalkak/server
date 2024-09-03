package com.ttalkak.project.project.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "projects")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    private Long userId;

    private String name;

    @Builder
    private ProjectEntity(Long id, Long userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public ProjectEditor.ProjectEditorBuilder toEditor() {
        return ProjectEditor.builder()
                .projectName(this.name);
    }

    public void edit(ProjectEditor projectEditor) {
        this.name = projectEditor.projectName;
    }
}
