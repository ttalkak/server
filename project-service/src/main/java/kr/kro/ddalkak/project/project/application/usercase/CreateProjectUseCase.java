package kr.kro.ddalkak.project.project.application.usercase;

import kr.kro.ddalkak.project.project.framework.web.request.ProjectCreateRequest;
import kr.kro.ddalkak.project.project.framework.web.response.ProjectResponse;

public interface CreateProjectUseCase {

    ProjectResponse createProject(ProjectCreateRequest projectCreateRequest);
}
