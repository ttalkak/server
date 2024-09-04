package com.ttalkak.project.project.application.usercase;

public interface DeleteProjectUseCase {
    void deleteProject(Long projectId);

    void rollbackStatusProject(Long projectId);
}
