package kr.kro.ddalkak.project.project.application.outputport;

import kr.kro.ddalkak.project.project.domain.model.ProjectEntity;

public interface LoadProjectOutputPort {

    ProjectEntity findById(Long projectId);
}
