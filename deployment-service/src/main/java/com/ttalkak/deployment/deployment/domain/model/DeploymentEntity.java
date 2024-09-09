package com.ttalkak.deployment.deployment.domain.model;

import com.ttalkak.deployment.deployment.domain.model.vo.DeploymentEditor;
import jakarta.persistence.*;
import com.ttalkak.deployment.common.BaseEntity;
import com.ttalkak.deployment.deployment.domain.model.vo.DeploymentStatus;
import com.ttalkak.deployment.deployment.domain.model.vo.GithubInfo;
import com.ttalkak.deployment.deployment.domain.model.vo.ServiceType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeploymentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private Long projectId;

    @Setter
    @Column(name = "deploy_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeploymentStatus status;

    @Setter
    @Column(name = "service_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deploymentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<HostingEntity> hostingEntities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deploymentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<DatabaseEntity> dataBaseEntities = new ArrayList<>();

    @Setter
    @Embedded
    private GithubInfo githubInfo;

    private String env;

    @Builder
    private DeploymentEntity(Long id, Long projectId, DeploymentStatus status, ServiceType serviceType, GithubInfo githubInfo, String env) {
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
                .status(DeploymentStatus.PENDING)
                .githubInfo(githubInfo)
                .env(env)
                .build();
    }

    public void addHostingEntity(HostingEntity hostingEntity){
        this.hostingEntities.add(hostingEntity);
    }

    public void addDatabaseEntity(DatabaseEntity databaseEntity){
        this.dataBaseEntities.add(databaseEntity);
    }

    public void deleteDeployment(){
        this.status = DeploymentStatus.DELETED;
    }

    public void runDeployment(){
        this.status = DeploymentStatus.RUNNING;
    }

    public void stopDeployment(){
        this.status = DeploymentStatus.STOP;
    }

    public DeploymentEditor.DeploymentEditorBuilder toEditor() {
        return DeploymentEditor.builder()
                .githubInfo(this.githubInfo)
                .env(this.env);
    }

    public void edit(DeploymentEditor deploymentEditor) {
        this.githubInfo = deploymentEditor.getGithubInfo();
        this.env = deploymentEditor.getEnv();


    }
}