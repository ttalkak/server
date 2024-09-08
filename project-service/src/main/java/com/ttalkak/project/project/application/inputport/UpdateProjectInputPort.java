package com.ttalkak.project.project.application.inputport;

import com.ttalkak.project.common.UseCase;
import com.ttalkak.project.project.application.outputport.LoadProjectOutputPort;
import com.ttalkak.project.project.application.outputport.SaveProjectOutputPort;
import com.ttalkak.project.project.application.usercase.UpdateProjectUseCase;
import com.ttalkak.project.project.domain.model.vo.ProjectEditor;
import com.ttalkak.project.project.domain.model.ProjectEntity;
import com.ttalkak.project.project.framework.web.request.ProjectUpdateRequest;
import com.ttalkak.project.project.framework.web.response.ProjectResponse;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateProjectInputPort implements UpdateProjectUseCase {

    private final LoadProjectOutputPort loadProjectOutputPort;

    private final SaveProjectOutputPort saveProjectOutputPort;

    // 프로젝트 변경
    @Override
    public ProjectResponse updateProject(Long projectId, ProjectUpdateRequest projectUpdateRequest) {
        ProjectEntity projectEntity = loadProjectOutputPort.findById(projectId);

        ProjectEditor.ProjectEditorBuilder projectEditorBuilder = projectEntity.toEditor();
        ProjectEditor projectEditor = projectEditorBuilder
                .projectName(projectUpdateRequest.getProjectName())
                .domainName(projectUpdateRequest.getDomainName())
                .build();

        projectEntity.edit(projectEditor);
        saveProjectOutputPort.save(projectEntity);
        return ProjectResponse.mapToResponse(projectEntity);
    }

}
