package kr.kro.ttalkak.deployment.deployment.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deployment_entity_id", nullable = false)
    private DeploymentEntity deploymentEntity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DatabaseType database;

    private String username;

    private String password;

    private int port;
}
