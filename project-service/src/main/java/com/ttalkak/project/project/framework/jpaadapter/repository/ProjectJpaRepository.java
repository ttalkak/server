package com.ttalkak.project.project.framework.jpaadapter.repository;

import com.ttalkak.project.project.domain.model.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, Long> {

    // 유저 Id로 페이징 처리
    @Query("select p from ProjectEntity p where p.userId =:userId ")
    Page<ProjectEntity> findMyProjects(Pageable pageable, @Param("userId")Long userId);

    // 유저 Id, 키워드로 페이징처리
    @Query("select p from ProjectEntity p where p.userId = :userId and lower(p.name) like lower(concat('%', :searchKeyword, '%'))")
    Page<ProjectEntity> findMyPrjectsContinsSearchKeyWord(Pageable pageable, @Param("userId")Long userId, @Param("searchKeyword")String searchKeyword);
}
