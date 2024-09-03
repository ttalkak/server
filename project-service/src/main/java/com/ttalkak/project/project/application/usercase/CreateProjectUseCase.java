package com.ttalkak.project.project.application.usercase;

import com.ttalkak.project.project.framework.web.request.ProjectCreateRequest;
import com.ttalkak.project.project.framework.web.response.ProjectResponse;

public interface CreateProjectUseCase {

    ProjectResponse createProject(ProjectCreateRequest projectCreateRequest);
}
