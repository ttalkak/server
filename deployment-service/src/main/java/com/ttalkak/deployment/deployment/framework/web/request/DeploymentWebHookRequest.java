package com.ttalkak.deployment.deployment.framework.web.request;

import com.ttalkak.deployment.deployment.framework.jpaadapter.repository.DeploymentRepository;
import lombok.Data;

@Data
public class DeploymentWebHookRequest {
    private WebHookRepository repository;
    private WebHookUser sender;
}
