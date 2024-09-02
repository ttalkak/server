package kr.kro.ddalkak.project.project.framework.web.response;

import kr.kro.ddalkak.project.project.domain.model.ProjectEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ProjectResponse {

    private Long id;

    private Long userId;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    private ProjectResponse(Long id, Long userId, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ProjectResponse mapToResponse(ProjectEntity projectEntity) {
        return ProjectResponse.builder()
                .id(projectEntity.getId())
                .userId(projectEntity.getUserId())
                .name(projectEntity.getName())
                .createdAt(projectEntity.getCreatedAt())
                .updatedAt(projectEntity.getUpdatedAt())
                .build();
    }

}
