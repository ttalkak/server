package com.ttalkak.project.project.application.outputport;

import com.ttalkak.project.project.domain.model.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoadProjectOutputPort {

    // 프로젝트 단건 조회
    ProjectEntity findById(Long projectId);

    // 프로젝트 단순 페이징 처리
    Page<ProjectEntity> findMyProjects(Pageable pageable, Long userId);

    // 프로젝트 키워드 포함 페이징 처리
    Page<ProjectEntity> findMyPrjectsContinsSearchKeyWord(Pageable pageable, Long userId, String searchKeyword);
}