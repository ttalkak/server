package com.ttalkak.project.project.application.usercase;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface DeleteProjectUseCase {
    void deleteProject(Long projectId) throws JsonProcessingException;

    void rollbackStatusProject(Long projectId);
}
