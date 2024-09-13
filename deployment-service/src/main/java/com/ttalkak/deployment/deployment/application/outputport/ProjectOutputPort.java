package com.ttalkak.deployment.deployment.application.outputport;


import com.ttalkak.deployment.deployment.framework.projectadapter.dto.ProjectInfoResponse;
import com.ttalkak.deployment.deployment.framework.projectadapter.dto.ProjectWebHookResponse;


public interface ProjectOutputPort {
    ProjectInfoResponse getProjectInfo(Long projectId);
    ProjectWebHookResponse getWebHookProject(String webhookToken);
}
