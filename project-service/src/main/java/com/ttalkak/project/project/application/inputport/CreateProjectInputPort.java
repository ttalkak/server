package com.ttalkak.project.project.application.inputport;

import com.ttalkak.project.common.UseCase;
import com.ttalkak.project.project.application.outputport.LoadProjectOutputPort;
import com.ttalkak.project.project.application.outputport.SaveProjectOutputPort;
import com.ttalkak.project.project.application.usercase.CreateProjectUseCase;
import com.ttalkak.project.project.application.usercase.GetProjectUseCase;
import com.ttalkak.project.project.domain.model.ProjectEntity;
import com.ttalkak.project.project.framework.web.request.ProjectCreateRequest;
import com.ttalkak.project.project.framework.web.response.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /**
     * 프로젝트 페이징 조회
     * @param pageable
     * @return
     */
    @Override
    public Page<ProjectResponse> getProjects(Pageable pageable, String searchKeyword, Long userId) {
        Page<ProjectEntity> projects = null;
        if(searchKeyword == null || searchKeyword.isEmpty()) {
            projects = loadProjectOutputPort.findMyProjects(pageable, userId);
        } else {
            projects = loadProjectOutputPort.findMyPrjectsContinsSearchKeyWord(pageable, userId, searchKeyword);
        }
        return projects
                .map(ProjectResponse::mapToResponse);
    }
}
