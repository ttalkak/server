package kr.kro.ddalkak.project.project.application.outputport;

import kr.kro.ddalkak.project.project.domain.model.ProjectEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveProjectOutputPort {

    ProjectEntity save(ProjectEntity projectEntity);

}
