package kr.kro.ttalkak.deployment.deployment.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeploymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long projectId;

    private boolean status;

    @Column(nullable = false)
    private String url;

    public DeploymentEntity(Long projectId, boolean status, String url) {
        this.projectId = projectId;
        this.status = status;
        this.url = url;
    }
}
