package kr.kro.ddalkak.project.project.framework.jpaadapter.repository;

import kr.kro.ddalkak.project.project.domain.model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, Long> {
}
