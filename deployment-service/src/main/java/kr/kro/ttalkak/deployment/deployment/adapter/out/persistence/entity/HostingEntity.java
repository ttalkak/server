package kr.kro.ttalkak.deployment.deployment.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class HostingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int port;

    private Long domainId;

    @Column(name = "user_id", nullable = false)
    private Long deployerId;

    @Column(nullable = false)
    private Long deployerIp;

    @ManyToOne
    @JoinColumn(name = "deployment_entity_id", nullable = false)
    private DeploymentEntity deploymentEntity;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
}
