package com.ttalkak.deployment.deployment.framework.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttalkak.deployment.deployment.application.usecase.CreateDeploymentUsecase;
import com.ttalkak.deployment.deployment.application.usecase.DeleteDeploymentUsecase;
import com.ttalkak.deployment.deployment.application.usecase.InquiryUsecase;
import com.ttalkak.deployment.deployment.application.usecase.UpdateDeploymentUsecase;
import com.ttalkak.deployment.deployment.framework.web.request.DatabaseCreateRequest;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentCreateRequest;
import com.ttalkak.deployment.deployment.framework.web.request.GithubRepositoryRequest;
import com.ttalkak.deployment.deployment.framework.web.request.HostingCreateRequest;
import com.ttalkak.deployment.deployment.framework.web.response.DeploymentResponse;
import com.ttalkak.deployment.deployment.framework.web.response.HostingResponse;
import com.ttalkak.deployment.deployment.support.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeploymentControllerTest extends RestDocsSupport {

    private final CreateDeploymentUsecase createDeploymentUsecase = mock(CreateDeploymentUsecase.class);

    private final UpdateDeploymentUsecase updateDeploymentUsecase = mock(UpdateDeploymentUsecase.class);

    private final DeleteDeploymentUsecase deleteDeploymentUsecase = mock(DeleteDeploymentUsecase.class);

    private final InquiryUsecase inquiryUsecase = mock(InquiryUsecase.class);

    @Override
    public Object initController() {
        return new DeploymentController(createDeploymentUsecase, updateDeploymentUsecase, deleteDeploymentUsecase, inquiryUsecase);
    }

    @DisplayName("배포 실행")
    @Test
    void createDeployment() throws Exception {
        //given
        GithubRepositoryRequest githubRepositoryRequest = GithubRepositoryRequest.builder()
                .repositoryName("repo-name")
                .repositoryUrl("https://github.com/repo-url")
                .repositoryLastCommitMessage("Initial commit")
                .repositoryLastCommitUserProfile("https://github.com/user/profile")
                .repositoryLastCommitUserName("userName")
                .rootDirectory("root/")
                .build();

        DatabaseCreateRequest databaseCreateRequest = DatabaseCreateRequest.builder()
                .databaseName("dbName")
                .databasePort(3306)
                .username("dbUser")
                .password("dbPassword")
                .build();

        HostingCreateRequest hostingCreateRequest = HostingCreateRequest.builder()
                .hostingPort(8080)
                .build();

        DeploymentCreateRequest request = DeploymentCreateRequest.builder()
                .projectId(1L)
                .serviceType("BACKEND")
                .githubRepositoryRequest(githubRepositoryRequest)
                .databaseCreateRequests(List.of(databaseCreateRequest))
                .hostingCreateRequest(hostingCreateRequest)
                .env("production")
                .build();

        HostingResponse hostingResponse1 = HostingResponse.builder()
                .hostingId(1L)
                .hostingPort(8080)
                .serviceType("BACKEND")
                .detailDomainName("api.leadme")
                .build();

        HostingResponse hostingResponse2 = HostingResponse.builder()
                .hostingId(2L)
                .hostingPort(5173)
                .serviceType("FRONTEND")
                .detailDomainName("leadme")
                .build();

        DeploymentResponse response = DeploymentResponse.builder()
                .deploymentId(1L)
                .projectId(1L)
                .status("READY")
                .serviceType("BACKEND")
                .repositoryName("repo-name")
                .repositoryUrl("https://github.com/repo-url")
                .repositoryLastCommitMessage("Initial commit")
                .repositoryLastCommitUserProfile("https://github.com/user/profile")
                .repositoryLastCommitUserName("userName")
                .hostingResponses(List.of(hostingResponse1, hostingResponse2))  // 호스팅 응답이 있다고 가정
                .build();



        when(createDeploymentUsecase.createDeployment(any(DeploymentCreateRequest.class)))
                .thenReturn(response);

        //when
        ResultActions perform = mockMvc.perform(
                post("/v1/deployment")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                // 어떤 요청을 넣고, 어떤 응답을 넣어줄 것인가?
                .andDo(document("deployment-create",
                        requestFields(
                                fieldWithPath("projectId").type(JsonFieldType.NUMBER)
                                        .description("프로젝트 아이디"),
                                fieldWithPath("serviceType").type(JsonFieldType.STRING)
                                        .description("서비스 유형 (예: FRONTEND, BACKEND)"),
                                fieldWithPath("githubRepositoryRequest.repositoryName").type(JsonFieldType.STRING)
                                        .description("깃허브 저장소 이름"),
                                fieldWithPath("githubRepositoryRequest.repositoryUrl").type(JsonFieldType.STRING)
                                        .description("깃허브 저장소 URL"),
                                fieldWithPath("githubRepositoryRequest.repositoryLastCommitMessage").type(JsonFieldType.STRING)
                                        .description("최근 커밋 메시지"),
                                fieldWithPath("githubRepositoryRequest.repositoryLastCommitUserProfile").type(JsonFieldType.STRING)
                                        .description("최근 커밋 유저 프로필 URL"),
                                fieldWithPath("githubRepositoryRequest.repositoryLastCommitUserName").type(JsonFieldType.STRING)
                                        .description("최근 커밋 유저 이름"),
                                fieldWithPath("githubRepositoryRequest.rootDirectory").type(JsonFieldType.STRING)
                                        .description("루트 디렉토리 경로"),
                                fieldWithPath("databaseCreateRequests[].databaseName").type(JsonFieldType.STRING)
                                        .description("데이터베이스 이름"),
                                fieldWithPath("databaseCreateRequests[].databasePort").type(JsonFieldType.NUMBER)
                                        .description("데이터베이스 포트"),
                                fieldWithPath("databaseCreateRequests[].username").type(JsonFieldType.STRING)
                                        .description("데이터베이스 사용자 이름"),
                                fieldWithPath("databaseCreateRequests[].password").type(JsonFieldType.STRING)
                                        .description("데이터베이스 비밀번호"),
                                fieldWithPath("hostingCreateRequest.hostingPort").type(JsonFieldType.NUMBER)
                                        .description("호스팅 포트"),
                                fieldWithPath("env").type(JsonFieldType.STRING)
                                        .description("환경변수")
                        ),
                        responseFields(
                                fieldWithPath("deploymentId").type(JsonFieldType.NUMBER)
                                        .description("생성된 배포의 ID"),
                                fieldWithPath("projectId").type(JsonFieldType.NUMBER)
                                        .description("프로젝트의 ID"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("배포 상태 (예: READY, RUNNING, ERROR)"),
                                fieldWithPath("serviceType").type(JsonFieldType.STRING)
                                        .description("서비스 유형 (예: FRONTEND, BACKEND)"),
                                fieldWithPath("repositoryName").type(JsonFieldType.STRING)
                                        .description("깃허브 저장소 이름"),
                                fieldWithPath("repositoryUrl").type(JsonFieldType.STRING)
                                        .description("깃허브 저장소 URL"),
                                fieldWithPath("repositoryLastCommitMessage").type(JsonFieldType.STRING)
                                        .description("최근 커밋 메시지"),
                                fieldWithPath("repositoryLastCommitUserProfile").type(JsonFieldType.STRING)
                                        .description("최근 커밋 유저 프로필 URL"),
                                fieldWithPath("repositoryLastCommitUserName").type(JsonFieldType.STRING)
                                        .description("최근 커밋 유저 이름"),
                                fieldWithPath("hostingResponses").type(JsonFieldType.ARRAY)
                                        .description("호스팅 응답 목록"),
                                fieldWithPath("hostingResponses[].hostingId").type(JsonFieldType.NUMBER)
                                        .description("호스팅 ID"),
                                fieldWithPath("hostingResponses[].hostingPort").type(JsonFieldType.NUMBER)
                                        .description("호스팅 포트"),
                                fieldWithPath("hostingResponses[].serviceType").type(JsonFieldType.STRING)
                                        .description("호스팅 서비스 타입"),
                                fieldWithPath("hostingResponses[].detailDomainName").type(JsonFieldType.STRING)
                                        .description("호스팅 서브 도메인 이름")
                        )
                ));


        //then

    }
}