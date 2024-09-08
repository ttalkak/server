package com.ttalkak.deployment.deployment.framework.web.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class HostingUpdateRequest {


    @NotNull(message = "호스팅 포트는 필수입니다.")
    private int hostingPort;


    @Builder
    private HostingUpdateRequest(int hostingPort) {
        this.hostingPort = hostingPort;
    }
}
