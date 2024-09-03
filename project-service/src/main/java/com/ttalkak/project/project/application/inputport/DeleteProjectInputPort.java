package com.ttalkak.project.project.application.inputport;

import com.ttalkak.project.common.UseCase;
import com.ttalkak.project.project.application.outputport.DeleteProjectOutputPort;
import com.ttalkak.project.project.application.usercase.DeleteProjectUseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteProjectInputPort implements DeleteProjectUseCase {

    private final DeleteProjectOutputPort deleteProjectOutputPort;

    /**
     * 프로젝트 삭제
     * @param projectId
     */
    @Override
    public void deleteProject(Long projectId) {
        deleteProjectOutputPort.deleteProject(projectId);
    }
}
