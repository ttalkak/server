package com.ttalkak.project.project.framework.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttalkak.project.common.ApiResponse;
import com.ttalkak.project.common.WebAdapter;
import com.ttalkak.project.project.application.usercase.CreateProjectUseCase;
import com.ttalkak.project.project.application.usercase.DeleteProjectUseCase;
import com.ttalkak.project.project.application.usercase.GetProjectUseCase;
import com.ttalkak.project.project.application.usercase.UpdateProjectUseCase;
import com.ttalkak.project.project.domain.model.ProjectEntity;
import com.ttalkak.project.project.framework.web.request.DomainNameRequest;
import com.ttalkak.project.project.framework.web.request.ProjectCreateRequest;
import com.ttalkak.project.project.framework.web.request.ProjectUpdateRequest;
import com.ttalkak.project.project.framework.web.response.DomainNameResponse;
import com.ttalkak.project.project.framework.web.response.ProjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProjectResponse> createProject(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestBody ProjectCreateRequest projectCreateRequest) {
        return new ApiResponse<>(true, "", 201, createProjectUseCase.createProject(userId, projectCreateRequest));
    }

    /**
     * 프로젝트 단건 조회
     * @param projectId
     * @return
     */
    @GetMapping("/project/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProjectResponse> getProject(@PathVariable Long projectId) {
        ProjectResponse projectResponse = getProjectUseCase.getProject(projectId);
        return ApiResponse.success(projectResponse);
    }

    /**
     * 프로젝트 페이징 조회
     * @param pageable
     * @return
     */
    @GetMapping("/project/search")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Page<ProjectResponse>> getProjectsByPageable(
            @PageableDefault(page = 0, size = 9, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = true) String searchKeyword,
            @RequestHeader("X-USER-ID") Long userId) {

            return ApiResponse.success(getProjectUseCase.getProjects(pageable, searchKeyword, userId));
    }

    /**
     * 프로젝트 수정
     * @param projectId
     * @param projectUpdateRequest
     * @return
     */
    @PatchMapping("/project/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProjectResponse> updateProject(@PathVariable Long projectId, @RequestBody ProjectUpdateRequest projectUpdateRequest) {


        return ApiResponse.success(updateProjectUseCase.updateProject(projectId, projectUpdateRequest));
    }

    /**
     * 프로젝트 삭제
     * @param projectId
     * @return
     */
    @DeleteMapping("/project/{projectId}")
    public ApiResponse<Void> deleteProject(@PathVariable Long projectId) throws JsonProcessingException {
        deleteProjectUseCase.deleteProject(projectId);
        return ApiResponse.success();
    }
    
    /**
     * 도메인명 중복 체크
     * @param request
     * @return
     */
    @PostMapping("/project/domain/check")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<DomainNameResponse> isDuplicateDomainName(@RequestBody DomainNameRequest request) {
        DomainNameResponse.DomainNameResponseBuilder responseBuilder = DomainNameResponse.builder();
        if(!getProjectUseCase.isDuplicateDomainName(request)) {
            responseBuilder.canMake(true).message("생성할 수 있는 도메인입니다.");
        } else {
            responseBuilder.canMake(false).message("이미 존재하는 도메인입니다.");
        }

        return ApiResponse.success(responseBuilder.build());

    }


}
