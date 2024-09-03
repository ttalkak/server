package kr.kro.ddalkak.project.project.framework.web;

import kr.kro.ddalkak.project.common.WebAdapter;
import kr.kro.ddalkak.project.project.application.usercase.CreateProjectUseCase;
import kr.kro.ddalkak.project.project.application.usercase.GetProjectUseCase;
import kr.kro.ddalkak.project.project.framework.web.request.ProjectCreateRequest;
import kr.kro.ddalkak.project.project.framework.web.response.ProjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;

    private final GetProjectUseCase getProjectUseCase;

    /**
     * 프로젝트 생성
     * @param projectCreateRequest
     * @return
     */
    @PostMapping("/project")
    public ResponseEntity<ProjectResponse> createProject(ProjectCreateRequest projectCreateRequest) {
        ProjectResponse projectResponse = createProjectUseCase.createProject(projectCreateRequest);
        return ResponseEntity.ok(projectResponse);
    }
    
    @GetMapping("/project")
    public ResponseEntity<ProjectResponse> getProject(Long projectId) {
        ProjectResponse projectResponse = getProjectUseCase.getProject(projectId);
        return ResponseEntity.ok(projectResponse);
    }
}
