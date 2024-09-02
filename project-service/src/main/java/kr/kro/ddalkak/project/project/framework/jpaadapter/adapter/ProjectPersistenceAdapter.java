package kr.kro.ddalkak.project.project.framework.jpaadapter.adapter;

import kr.kro.ddalkak.project.common.PersistenceAdapter;
import kr.kro.ddalkak.project.global.error.ErrorCode;
import kr.kro.ddalkak.project.global.exception.EntityNotFoundException;
import kr.kro.ddalkak.project.project.application.outputport.LoadProjectOutputPort;
import kr.kro.ddalkak.project.project.domain.model.ProjectEntity;
import kr.kro.ddalkak.project.project.framework.jpaadapter.repository.ProjectJpaRepository;
import kr.kro.ddalkak.project.project.application.outputport.SaveProjectOutputPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProjectPersistenceAdapter implements SaveProjectOutputPort, LoadProjectOutputPort {
    private final ProjectJpaRepository projectJpaRepository;

    @Override
    public ProjectEntity save(ProjectEntity projectEntity) {
        return projectJpaRepository.save(projectEntity);
    }

    @Override
    public ProjectEntity findById(Long projectId) {
        return projectJpaRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXISTS_PROJECT));
    }

}
