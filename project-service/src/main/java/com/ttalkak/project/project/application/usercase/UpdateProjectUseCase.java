package com.ttalkak.project.project.application.usercase;

import com.ttalkak.project.project.framework.web.request.ProjectUpdateRequest;
import com.ttalkak.project.project.framework.web.response.ProjectResponse;

public interface UpdateProjectUseCase {
    ProjectResponse updateProject(Long projectId, ProjectUpdateRequest projectUpdateRequest);
}
