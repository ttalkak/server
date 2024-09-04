package com.ttalkak.deployment.deployment.framework.web.response;

import com.ttalkak.deployment.deployment.domain.model.DeploymentEntity;
import com.ttalkak.deployment.deployment.domain.model.HostingEntity;
import com.ttalkak.deployment.deployment.domain.model.vo.ServiceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HostingResponse {

    private Long hostingId;

    private String detailDomainName;


    private String serviceType;


    private int hostingPort;

    @Builder
    private HostingResponse(Long hostingId, String detailDomainName, String serviceType, int hostingPort) {
        this.hostingId = hostingId;
        this.detailDomainName = detailDomainName;
        this.serviceType = serviceType;
        this.hostingPort = hostingPort;
    }

    public static HostingResponse mapToDTO(HostingEntity hostingEntity){
        return HostingResponse.builder()
                .hostingId(hostingEntity.getId())
                .serviceType(hostingEntity.getServiceType().toString())
                .detailDomainName(hostingEntity.getDetailSubDomainName())
                .hostingPort(hostingEntity.getHostingPort())
                .build();
    }

}
