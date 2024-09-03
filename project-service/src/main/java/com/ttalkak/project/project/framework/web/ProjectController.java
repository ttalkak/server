package com.ttalkak.project.project.framework.web;

import com.ttalkak.project.common.WebAdapter;
import com.ttalkak.project.project.application.usercase.CreateProjectUseCase;
import com.ttalkak.project.project.application.usercase.DeleteProjectUseCase;
import com.ttalkak.project.project.application.usercase.GetProjectUseCase;
import com.ttalkak.project.project.application.usercase.UpdateProjectUseCase;
import com.ttalkak.project.project.domain.model.ProjectEntity;
import com.ttalkak.project.project.framework.web.request.ProjectCreateRequest;
import com.ttalkak.project.project.framework.web.request.ProjectUpdateRequest;
import com.ttalkak.project.project.framework.web.response.ProjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;

    private final GetProjectUseCase getProjectUseCase;

    private final UpdateProjectUseCase updateProjectUseCase;

    private final DeleteProjectUseCase deleteProjectUseCase;

    /**
     * 프로젝트 생성
     * @param projectCreateRequest
     * @return
     */
    @PostMapping("/project")
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectCreateRequest projectCreateRequest) {
        ProjectResponse projectResponse = createProjectUseCase.createProject(projectCreateRequest);
        return ResponseEntity.ok(projectResponse);
    }

    /**
     * 프로젝트 단건 조회
     * @param projectId
     * @return
     */
    @GetMapping("/project/{projectId}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long projectId) {
        ProjectResponse projectResponse = getProjectUseCase.getProject(projectId);
        return ResponseEntity.ok(projectResponse);
    }

    /**
     * 프로젝트 페이징 조회
     * @param pageable
     * @return
     */
    @GetMapping("/project/search")
    public ResponseEntity<Page<ProjectResponse>> getProjectsByPageable(
            @PageableDefault(page = 0, size = 9, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = true) String searchKeyword,
            @RequestParam(required = true) Long userId) {
            return ResponseEntity.ok(getProjectUseCase.getProjects(pageable, searchKeyword, userId));
    }

    /**
     * 프로젝트 수정
     * @param projectId
     * @param projectUpdateRequest
     * @return
     */
    @PatchMapping("/project/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long projectId, @RequestBody ProjectUpdateRequest projectUpdateRequest) {
        return ResponseEntity.ok(updateProjectUseCase.updateProject(projectId, projectUpdateRequest));
    }

    /**
     * 프로젝트 삭제
     * @param projectId
     * @return
     */
    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        deleteProjectUseCase.deleteProject(projectId);
        return ResponseEntity.ok().build();
    }


}
