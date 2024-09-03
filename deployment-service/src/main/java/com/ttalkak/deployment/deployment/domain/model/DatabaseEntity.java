package com.ttalkak.deployment.deployment.domain.model;

import jakarta.persistence.*;
import com.ttalkak.deployment.deployment.domain.model.vo.DatabaseType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deploymentEntity", nullable = false)
    private DeploymentEntity deploymentEntity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DatabaseType databaseType;

    private String username;

    private String password;

    private int port;

    @Builder
    private DatabaseEntity(DeploymentEntity deploymentEntity, DatabaseType databaseType, String username, String password, int port) {
        this.deploymentEntity = deploymentEntity;
        this.databaseType = databaseType;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public static DatabaseEntity createDatabase(DeploymentEntity deploymentEntity,
                                                int port,
                                                String databaseType,
                                                String username,
                                                String password) {
        return DatabaseEntity.builder()
                .deploymentEntity(deploymentEntity)
                .port(port)
                .databaseType(DatabaseType.valueOf(databaseType))
                .username(username)
                .password(password)
                .build();

    }
}
