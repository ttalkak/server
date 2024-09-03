package kr.kro.ddalkak.project.project.application.inputport;

import kr.kro.ddalkak.project.common.UseCase;
import kr.kro.ddalkak.project.project.application.outputport.LoadProjectOutputPort;
import kr.kro.ddalkak.project.project.application.outputport.SaveProjectOutputPort;
import kr.kro.ddalkak.project.project.application.usercase.CreateProjectUseCase;
import kr.kro.ddalkak.project.project.application.usercase.GetProjectUseCase;
import kr.kro.ddalkak.project.project.domain.model.ProjectEntity;
import kr.kro.ddalkak.project.project.framework.web.request.ProjectCreateRequest;
import kr.kro.ddalkak.project.project.framework.web.response.ProjectResponse;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateProjectInputPort implements CreateProjectUseCase, GetProjectUseCase {

    private final SaveProjectOutputPort saveProjectOutputPort;

    private final LoadProjectOutputPort loadProjectOutputPort;

    /**
     * 프로젝트 생성
     * @param projectCreateRequest
     * @return
     */
    @Override
    public ProjectResponse createProject(ProjectCreateRequest projectCreateRequest) {

        ProjectEntity projectEntity = ProjectEntity.builder()
                .name(projectCreateRequest.getProjectName())
                .userId(projectCreateRequest.getUserId())
                .build();

        ProjectEntity result = saveProjectOutputPort.save(projectEntity);

        return ProjectResponse.mapToResponse(result);
    }

    /**
     * 프로젝트 단건 조회
     * @param projectId
     * @return
     */
    @Override
    public ProjectResponse getProject(Long projectId) {
        ProjectEntity result = loadProjectOutputPort.findById(projectId);
        return ProjectResponse.mapToResponse(result);
    }
}
