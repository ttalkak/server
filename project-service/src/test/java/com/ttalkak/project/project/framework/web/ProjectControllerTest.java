package com.ttalkak.project.project.framework.web;

import com.ttalkak.project.project.application.usercase.CreateProjectUseCase;
import com.ttalkak.project.project.framework.web.request.ProjectCreateRequest;
import com.ttalkak.project.project.framework.web.response.ProjectResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static reactor.core.publisher.Mono.when;

@DisplayName("프롲게트 컨트롤러 테스트")
@WebMvcTest(value = ProjectController.class)
class ProjectControllerTest {

    @MockBean
    private CreateProjectUseCase createProjectUseCase;
    @Test
    @DisplayName("프로젝트 생성 테스트")
    void 프로젝트_생성_테스트() throws Exception {
        // given
        ProjectCreateRequest request = ProjectCreateRequest.builder()
                .projectName("프로젝트V1")
                .userId(1L)
                .build();

        ProjectResponse response = ProjectResponse.builder()
                .name("프로젝트V1")
                .userId(1L)
                .build();

        when((Publisher<?>) createProjectUseCase.createProject(any(ProjectCreateRequest.class)))
                .thenReturn(response);

    }

    @Test
    void getProject() {
    }

    @Test
    void getProjectsByPageable() {
    }

    @Test
    void updateProject() {
    }

    @Test
    void deleteProject() {
    }
}