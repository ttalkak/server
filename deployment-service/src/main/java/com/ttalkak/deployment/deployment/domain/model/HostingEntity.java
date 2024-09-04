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

    private String detailSubDomainName;

    private String detailSubDomainKey;



    @Builder
    private HostingEntity(int hostingPort, Long deployerId, String hostingIp, DeploymentEntity deploymentEntity, ServiceType serviceType, String detailSubDomainName, String detailSubDomainKey) {
        this.hostingPort = hostingPort;
        this.deployerId = deployerId;
        this.hostingIp = hostingIp;
        this.deploymentEntity = deploymentEntity;
        this.serviceType = serviceType;
        this.detailSubDomainName = detailSubDomainName;
        this.detailSubDomainKey = detailSubDomainKey;
    }

    public static HostingEntity createHosting(DeploymentEntity deploymentEntity,
                                              int hostingPort,
                                              String serviceType,
                                              String projectDomainName){
        return HostingEntity.builder()
                .deploymentEntity(deploymentEntity)
                .hostingPort(hostingPort)
                .serviceType(ServiceType.valueOf(serviceType))
                .detailSubDomainName(changeDetailDomainName(projectDomainName, serviceType))
                .build();
    }

    private static String changeDetailDomainName(String projectDomainName, String serviceType) {
        if(ServiceType.isBackendType(serviceType)){
            return "api." + projectDomainName;
        }

        else if(ServiceType.isFrontendType(serviceType)){
            return projectDomainName;
        }

        throw new IllegalArgumentException("ServiceType이 잘못 입력되었습니다.");
    }

    public void setHostingPort(String hostingIp){
        this.hostingIp = hostingIp;
    }


    public void setDeployerId(Long deployerId){
        this.deployerId = deployerId;
    }

    public void setDetailSubDomainKey(String detailSubDomainKey){
        this.detailSubDomainKey = detailSubDomainKey;
    }

}
