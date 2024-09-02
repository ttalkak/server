package com.ttalkak.deployment.deployment.domain.model;

import jakarta.persistence.*;
import com.ttalkak.deployment.deployment.domain.model.vo.ServiceType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class HostingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int hostingPort;

    private Long domainId;


    // 처음 호스팅객체가 생성되었을때는 제공자와 호스팅아이피가 null값이어야함.
    @Column(name = "user_id", nullable = true)
    private Long deployerId;

    @Column(nullable = true)
    private String hostingIp;

    @ManyToOne
    @JoinColumn(name = "deploymentEntity", nullable = false)
    private DeploymentEntity deploymentEntity;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Builder
    private HostingEntity(int hostingPort, Long domainId, Long deployerId, String hostingIp, DeploymentEntity deploymentEntity, ServiceType serviceType) {
        this.hostingPort = hostingPort;
        this.domainId = domainId;
        this.deployerId = deployerId;
        this.hostingIp = hostingIp;
        this.deploymentEntity = deploymentEntity;
        this.serviceType = serviceType;
    }

    public static HostingEntity createHosting(DeploymentEntity deploymentEntity,
                                              int hostingPort,
                                              Long domainId,
                                              String serviceType){
        return HostingEntity.builder()
                .deploymentEntity(deploymentEntity)
                .hostingPort(hostingPort)
                .domainId(domainId)
                .serviceType(ServiceType.valueOf(serviceType))
                .build();
    }

    public void setHostingPort(String hostingIp){
        this.hostingIp = hostingIp;
    }


    public void setDeployerId(Long deployerId){
        this.deployerId = deployerId;
    }
}
