package com.ttalkak.project.project.framework.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttalkak.project.project.application.usercase.CreateProjectUseCase;
import com.ttalkak.project.project.application.usercase.DeleteProjectUseCase;
import com.ttalkak.project.project.application.usercase.GetProjectUseCase;
import com.ttalkak.project.project.application.usercase.UpdateProjectUseCase;
import com.ttalkak.project.project.config.RestDocsUtils;
import com.ttalkak.project.project.framework.deploymentadapter.dto.DeploymentResponse;
import com.ttalkak.project.project.framework.deploymentadapter.dto.EnvResponse;
import com.ttalkak.project.project.framework.deploymentadapter.dto.HostingResponse;
import com.ttalkak.project.project.framework.web.request.DomainNameRequest;
import com.ttalkak.project.project.framework.web.request.ProjectCreateRequest;
import com.ttalkak.project.project.framework.web.request.ProjectUpdateRequest;
import com.ttalkak.project.project.framework.web.response.DomainNameResponse;
import com.ttalkak.project.project.framework.web.response.ProjectCreateResponse;
import com.ttalkak.project.project.framework.web.response.ProjectPageResponse;
import com.ttalkak.project.project.framework.web.response.ProjectResponse;
import com.ttalkak.project.project.support.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("프로젝트 컨트롤러 테스트")
class ProjectControllerTest extends RestDocsSupport {

    @Override
    public Object initController() {
        return new ProjectController(createProjectUseCase, getProjectUseCase, updateProjectUseCase, deleteProjectUseCase);
    }

    private CreateProjectUseCase createProjectUseCase = mock(CreateProjectUseCase.class);

    private GetProjectUseCase getProjectUseCase = mock(GetProjectUseCase.class);

    private UpdateProjectUseCase updateProjectUseCase = mock(UpdateProjectUseCase.class);

    private DeleteProjectUseCase deleteProjectUseCase = mock(DeleteProjectUseCase.class);

    LocalDateTime fixedClock = LocalDateTime.of(2024, 1, 1, 0, 0, 0,0);

    @Test
    @DisplayName("프로젝트 생성 테스트")
    void 프로젝트_생성_테스트() throws Exception {

        // given
        ProjectCreateRequest projectCreateRequest = new ProjectCreateRequest("project", "domain");
        ProjectCreateResponse projectCreateResponse = ProjectCreateResponse.builder()
                .id(1L)
                .userId(1L)
                .projectName("project")
                .domainName("domain")
                .createdAt(fixedClock)
                .updatedAt(fixedClock)
                .build();

        Long userId = 1L;
        when(createProjectUseCase.createProject(eq(userId), any(ProjectCreateRequest.class)))
                .thenReturn(projectCreateResponse);

        // when
        ResultActions perform = mockMvc.perform(
                post("/v1/project")
                        .header("X-USER-ID", 1L)
                        .content(objectMapper.writeValueAsString(projectCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON)

            )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("project-create",
                        requestFields(
                                fieldWithPath("projectName").type(JsonFieldType.STRING)
                                        .description("프로젝트명"),
                                fieldWithPath("domainName").type(JsonFieldType.STRING)
                                        .description("도메인명")
                        ),
                        responseFields(
                                RestDocsUtils.combineFields(
                                        RestDocsUtils.getCommonResponseFields(),
                                        RestDocsUtils.getProjectResponseFields()
                                )
                        )
                ));

        // then
        perform.andDo(print());
    }

    @Test
    @DisplayName("프로젝트 조회 테스트")
    void 프로젝트_조회_테스트() throws Exception{

        // given
        EnvResponse env1 = EnvResponse.builder()
                .envId("1")
                .key("key1")
                .value("value1")
                .build();

        EnvResponse env2 = EnvResponse.builder()
                .envId("2")
                .key("key2")
                .value("value2")
                .build();

        List<EnvResponse> envResponses = List.of(env1, env2);

        HostingResponse hostingResponse = HostingResponse.builder()
                .hostingId(1L)
                .hostingPort(8080)
                .serviceType("BACKEND")
                .detailDomainName("api.leadme")
                .build();

        List<HostingResponse> hostingResponses = List.of(hostingResponse);

        DeploymentResponse deploymentResponse = DeploymentResponse.builder()
                .deploymentId(1L)
                .projectId(1L)
                .status("RUNNING")
                .serviceType("BACKEND")
                .repositoryName("repo-name")
                .repositoryUrl("https://github.com/repo-url")
                .repositoryLastCommitMessage("Initial commit")
                .repositoryLastCommitUserProfile("https://github.com/user/profile")
                .repositoryLastCommitUserName("userName")
                .rootDirectory("root/")
                .branch("main")
                .framework("SPRING")
                .envs(envResponses)
                .hostingResponses(hostingResponses)
                .build();

        List<DeploymentResponse> deploymentResponses = List.of(deploymentResponse);

        ProjectResponse projectResponse = ProjectResponse.builder()
                .id(1L)
                .userId(1L)
                .projectName("project")
                .domainName("domain")
                .createdAt(fixedClock)
                .updatedAt(fixedClock)
                .build();

        projectResponse.setDeployments(deploymentResponses);

        Long projectId = 1L;
        when(getProjectUseCase.getProject(eq(projectId)))
                .thenReturn(projectResponse);

        // when
        ResultActions perform = mockMvc.perform(
                get("/v1/project/{projectId}",1)
                )
                .andDo(result -> {
                    System.out.println("Actual Response: " + result.getResponse().getContentAsString());
                })
                .andExpect(status().isOk())
                .andDo(document("get project",
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 ID")
                        ),
                        responseFields(
                                RestDocsUtils.combineFields(
                                        RestDocsUtils.getCommonResponseFields(),
                                        RestDocsUtils.getProjectResponseFields(),
                                        RestDocsUtils.getDeploymentsField()
                                )
                        )
                ));

        // then
        perform.andDo(print());

    }

    @Test
    @DisplayName("프로젝트 페이징 조회")
    void 프로젝트_페이징_조회() throws Exception {

        // given
        Long userId = 1L;

        ProjectResponse projectResponse1 = ProjectResponse.builder()
                .id(1L)
                .userId(1L)
                .projectName("project1")
                .domainName("domain1")
                .createdAt(fixedClock)
                .updatedAt(fixedClock)
                .build();
        projectResponse1.setDeployments(new ArrayList<>());

        ProjectResponse projectResponse2 = ProjectResponse.builder()
                .id(1L)
                .userId(1L)
                .projectName("project2")
                .domainName("domain2")
                .createdAt(fixedClock)
                .updatedAt(fixedClock)
                .build();

        projectResponse2.setDeployments(new ArrayList<>());

        ProjectPageResponse projectPageResponse = ProjectPageResponse.builder()
                .content(Arrays.asList(projectResponse1, projectResponse2))
                .build();

        when(getProjectUseCase.getProjects(any(Pageable.class), any(String.class), eq(userId))).thenReturn(projectPageResponse);

        // when
        ResultActions perform = mockMvc.perform(
                get("/v1/project/search")
                        .param("page", "0")
                        .param("size", "9")
                        .param("sort", "createdAt,DESC")
                        .param("searchKeyword", "테스트")
                        .header("X-USER-ID", userId.toString())
                )
                .andDo(result -> {
                    System.out.println("Actual Response: " + result.getResponse().getContentAsString());
                })
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-projects",
                        responseFields(
                                RestDocsUtils.combineFields(
                                        RestDocsUtils.getCommonResponseFields(),
                                        RestDocsUtils.getProjectPages()
                                )
                        )
                ));
        // then
        perform.andDo(print());

    }

    @Test
    void updateProject() throws Exception {

        // given
        ProjectUpdateRequest projectUpdateRequest = ProjectUpdateRequest.builder()
                .projectName("updatedProject")
                .domainName("updatedDomain")
                .build();

        ProjectResponse projectResponse = ProjectResponse
                .builder()
                .userId(1L)
                .id(1L)
                .projectName("updatedProject")
                .domainName("updatedDomain")
                .createdAt(fixedClock)
                .updatedAt(fixedClock)
                .build();

        when(updateProjectUseCase.updateProject(eq(1L), any(ProjectUpdateRequest.class))).thenReturn(projectResponse);

        // when
        ResultActions perform = mockMvc.perform(
                patch("/v1/project/{projectId}", 1)
                        .header("X-USER-ID", 1L)
                        .content(objectMapper.writeValueAsString(projectUpdateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(result -> {
                    System.out.println("Actual Response: " + result.getResponse().getContentAsString());
                })
                .andExpect(status().isOk())
                .andDo(document("update-project",
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 ID")
                        ),
                        responseFields(
                                RestDocsUtils.combineFields(
                                        RestDocsUtils.getCommonResponseFields(),
                                        RestDocsUtils.getProjectResponseFields(),
                                        new FieldDescriptor[]{
                                                fieldWithPath("data.deployments").type(JsonFieldType.NULL).description("배포 목록")
                                        }
                                )
                        )
                ));

        // then
        perform.andDo(print());
    }

    @Test
    void deleteProject() throws Exception {

        // given
        Long projectId = 1L;

        doNothing().when(deleteProjectUseCase).deleteProject(eq(projectId));

        // when
        ResultActions perform = mockMvc.perform(
                delete("/v1/project/{projectId}", projectId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    System.out.println("Actual Response: " + result.getResponse().getContentAsString());
                })
                .andExpect(status().isOk());

        // then
        perform.andDo(print());

    }

    @Test
    @DisplayName("중복되지 않는 도메인")
    void 중복되지_않는_도메인() throws Exception {

        // given
        DomainNameRequest domainNameRequest = new DomainNameRequest("domain");

        DomainNameResponse domainNameResponse = DomainNameResponse.builder()
                .message("생성할 수 있는 도메인입니다.")
                .canMake(true)
                .build();

        when(getProjectUseCase.isDuplicateDomainName(domainNameRequest)).thenReturn(false);

        // when
        ResultActions perform = mockMvc.perform(
                        post("/v1/project/domain/check")
                                .content(objectMapper.writeValueAsString(domainNameRequest))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    System.out.println("Actual Response: " + result.getResponse().getContentAsString());
                })
                .andExpect(status().isOk())
                .andDo(document("update-project",
                        requestFields(
                                fieldWithPath("domainName").type(JsonFieldType.STRING).description("도메인명")
                        ),
                        responseFields(
                                RestDocsUtils.combineFields(
                                    RestDocsUtils.getCommonResponseFields(),
                                    new FieldDescriptor[] {
                                            fieldWithPath("data.message").type(JsonFieldType.STRING).description("메시지"),
                                            fieldWithPath("data.canMake").type(JsonFieldType.BOOLEAN).description("중복 여부")
                                    }
                                )

                        )
                ));
        // then
        perform.andDo(print());
    }

}