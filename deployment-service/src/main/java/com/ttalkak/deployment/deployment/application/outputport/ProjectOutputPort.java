package com.ttalkak.deployment.deployment.application.outputport;


import com.ttalkak.deployment.deployment.framework.projectadapter.dto.ProjectInfoResponse;


public interface ProjectOutputPort {

    ProjectInfoResponse getProjectInfo(Long projectId);
}
