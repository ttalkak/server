package kr.kro.ddalkak.project.project.application.usercase;

import kr.kro.ddalkak.project.project.framework.web.response.ProjectResponse;

public interface GetProjectUseCase {

    ProjectResponse getProject(Long projectId);
}
