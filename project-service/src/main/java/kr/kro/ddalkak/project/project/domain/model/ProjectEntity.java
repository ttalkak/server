package kr.kro.ddalkak.project.project.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
}
