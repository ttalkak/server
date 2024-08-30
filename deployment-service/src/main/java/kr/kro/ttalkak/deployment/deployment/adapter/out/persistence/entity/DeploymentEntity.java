package kr.kro.ttalkak.deployment.deployment.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeploymentEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long projectId;

    @Column(name = "deploy_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeployStatus status;

    @Column(nullable = false)
    private String url;

    @Column(name = "service_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hosting_entity", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<HostingEntity> hostingEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "database_entity", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<DatabaseEntity> dataBaseEntities = new ArrayList<>();

    @Column(nullable = false)
    private String repositoryName;

    @Column(nullable = false)
    private String repositoryUrl;

    @Column(nullable = false)
    private String repositoryLastCommitMessage;

    @Column(nullable = false)
    private String repositoryLastCommitUserProfile;

    @Column(nullable = false)
    private String repositoryLastCommitUserName;
}
