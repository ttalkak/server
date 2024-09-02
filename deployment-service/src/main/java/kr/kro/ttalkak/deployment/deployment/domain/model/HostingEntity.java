package kr.kro.ttalkak.deployment.deployment.domain.model;

import jakarta.persistence.*;
import kr.kro.ttalkak.deployment.deployment.domain.vo.ServiceType;
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

    @Column(name = "user_id", nullable = false)
    private Long deployerId;

    @Column(nullable = false)
    private String hostingIp;

    @ManyToOne
    @JoinColumn(name = "deployment_entity_id", nullable = false)
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
                                              Long deployerId,
                                              String hostingIp,
                                              Long domainId,
                                              String serviceType){
        return HostingEntity.builder()
                .deployerId(deployerId)
                .hostingIp(hostingIp)
                .hostingPort(hostingPort)
                .deploymentEntity(deploymentEntity)
                .domainId(domainId)
                .serviceType(ServiceType.valueOf(serviceType))
                .build();
    }
}
