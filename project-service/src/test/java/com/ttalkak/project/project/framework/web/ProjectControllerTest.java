package com.ttalkak.project.project.framework.web;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttalkak.project.project.application.usercase.CreateProjectUseCase;
import com.ttalkak.project.project.application.usercase.DeleteProjectUseCase;
import com.ttalkak.project.project.application.usercase.GetProjectUseCase;
import com.ttalkak.project.project.application.usercase.UpdateProjectUseCase;
import com.ttalkak.project.project.framework.web.request.ProjectCreateRequest;
import com.ttalkak.project.project.framework.web.response.ProjectResponse;
import com.ttalkak.project.project.support.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@DisplayName("프로젝트 컨트롤러 테스트")
@WebMvcTest(value = ProjectController.class)
class ProjectControllerTest extends RestDocsSupport {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateProjectUseCase createProjectUseCase;

    @MockBean
    private GetProjectUseCase getProjectUseCase;

    @MockBean
    private UpdateProjectUseCase updateProjectUseCase;

    @MockBean
    private DeleteProjectUseCase deleteProjectUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("프로젝트 생성 테스트")
    void 프로젝트_생성_테스트() {

        ProjectCreateRequest projectCreateRequest = new ProjectCreateRequest("project", "domain");
        ProjectResponse projectResponse = ProjectResponse.builder()
                .id(1L)
                .projectName("project")
                .domainName("domain")
                .build();

        Long userId = 1L;
        when(createProjectUseCase.createProject(eq(userId), any(ProjectCreateRequest.class)))
                .thenReturn(projectResponse);

        mockMvc.perform(post("/project")
                        .header("X-USER-ID", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectCreateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.statusCode").value(201))
                .andExpect(jsonPath("$.data").exists());
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

    @Test
    void isDuplicateDomainName() {
    }
}