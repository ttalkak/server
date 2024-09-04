package com.ttalkak.project.project.framework.web.response;

import com.ttalkak.project.project.domain.model.ProjectEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ProjectResponse {

    private Long id;

    private Long userId;

    private String projectName;

    private String domainName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    private ProjectResponse(Long id, Long userId, String projectName, String domainName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.projectName = projectName;
        this.domainName = domainName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ProjectResponse mapToResponse(ProjectEntity projectEntity) {
        return ProjectResponse.builder()
                .id(projectEntity.getId())
                .userId(projectEntity.getUserId())
                .projectName(projectEntity.getProjectName())
                .domainName(projectEntity.getDomainName())
                .createdAt(projectEntity.getCreatedAt())
                .updatedAt(projectEntity.getUpdatedAt())
                .build();
    }

}
