package com.ttalkak.deployment.deployment.domain.model;

import jakarta.persistence.*;
import com.ttalkak.deployment.common.BaseEntity;
import com.ttalkak.deployment.deployment.domain.model.vo.DeployStatus;
import com.ttalkak.deployment.deployment.domain.model.vo.GithubInfo;
import com.ttalkak.deployment.deployment.domain.model.vo.ServiceType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeploymentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long projectId;

    @Column(name = "deploy_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeployStatus status;

    @Column(name = "service_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deploymentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<HostingEntity> hostingEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deploymentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<DatabaseEntity> dataBaseEntities = new ArrayList<>();

    @Embedded
    private GithubInfo githubInfo;

    private String env;

    @Builder
    private DeploymentEntity(Long id, Long projectId, DeployStatus status, ServiceType serviceType, GithubInfo githubInfo, String env) {
        this.id = id;
        this.projectId = projectId;
        this.status = status;
        this.serviceType = serviceType;
        this.githubInfo = githubInfo;
        this.env = env;
    }



    // 배포 생성
    public static DeploymentEntity createDeployment(Long projectId, ServiceType ServiceType, GithubInfo githubInfo, String env){
        return DeploymentEntity.builder()
                .projectId(projectId)
                .serviceType(ServiceType)
                .status(DeployStatus.READY)
                .githubInfo(githubInfo)
                .env(env)
                .build();
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setStatus(DeployStatus status) {
        this.status = status;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public void setGithubInfo(GithubInfo githubInfo) {
        this.githubInfo = githubInfo;
    }

    public void addHostingEntity(HostingEntity hostingEntity){
        this.hostingEntities.add(hostingEntity);
    }


    public void addDatabaseEntity(DatabaseEntity databaseEntity){
        this.dataBaseEntities.add(databaseEntity);
    }

}