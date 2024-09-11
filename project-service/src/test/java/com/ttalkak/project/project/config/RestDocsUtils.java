package com.ttalkak.project.project.config;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class RestDocsUtils {

    public static FieldDescriptor[] getCommonResponseFields() {
        return new FieldDescriptor[] {
                fieldWithPath("success").type(JsonFieldType.BOOLEAN)
                        .description("요청 성공 여부"),
                fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("응답 메시지 (현재 null)"),
                fieldWithPath("status").type(JsonFieldType.NUMBER)
                        .description("응답 코드 (예: 201)")
        };
    }

    public static FieldDescriptor[] getProjectResponseFields() {
        return new FieldDescriptor[] {
                fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                        .description("프로젝트 ID"),
                fieldWithPath("data.userId").type(JsonFieldType.NUMBER)
                        .description("유저 ID"),
                fieldWithPath("data.projectName").type(JsonFieldType.STRING)
                        .description("프로젝트명"),
                fieldWithPath("data.domainName").type(JsonFieldType.STRING)
                        .description("도메인명"),
                fieldWithPath("data.createdAt").type(JsonFieldType.ARRAY)
                        .description("생성일시"),
                fieldWithPath("data.updatedAt").type(JsonFieldType.ARRAY)
                        .description("업데이트일시")
        };
    }

    public static FieldDescriptor[] getDeploymentsField() {
        return new FieldDescriptor[] {
                fieldWithPath("data.deployments").type(JsonFieldType.ARRAY)
                        .description("배포 목록"),
                fieldWithPath("data.deployments[].deploymentId").type(JsonFieldType.NUMBER)
                        .description("생성된 배포의 ID"),
                fieldWithPath("data.deployments[].projectId").type(JsonFieldType.NUMBER)
                        .description("프로젝트의 ID"),
                fieldWithPath("data.deployments[].status").type(JsonFieldType.STRING)
                        .description("배포 상태 (예: READY, RUNNING, ERROR)"),
                fieldWithPath("data.deployments[].serviceType").type(JsonFieldType.STRING)
                        .description("서비스 유형 (예: FRONTEND, BACKEND)"),
                fieldWithPath("data.deployments[].repositoryName").type(JsonFieldType.STRING)
                        .description("깃허브 저장소 이름"),
                fieldWithPath("data.deployments[].repositoryUrl").type(JsonFieldType.STRING)
                        .description("깃허브 저장소 URL"),
                fieldWithPath("data.deployments[].repositoryLastCommitMessage").type(JsonFieldType.STRING)
                        .description("최근 커밋 메시지"),
                fieldWithPath("data.deployments[].repositoryLastCommitUserProfile").type(JsonFieldType.STRING)
                        .description("최근 커밋 유저 프로필 URL"),
                fieldWithPath("data.deployments[].repositoryLastCommitUserName").type(JsonFieldType.STRING)
                        .description("최근 커밋 유저 이름"),
                fieldWithPath("data.deployments[].rootDirectory")
                        .description("루트 디렉토리"),
                fieldWithPath("data.deployments[].branch").type(JsonFieldType.STRING)
                        .description("깃허브 브랜치"),
                fieldWithPath("data.deployments[].framework").type(JsonFieldType.STRING)
                        .description("사용된 프레임워크"),

                fieldWithPath("data.deployments[].envs").type(JsonFieldType.ARRAY)
                        .description("환경 변수 목록"),
                fieldWithPath("data.deployments[].envs[].envId").type(JsonFieldType.STRING)
                        .description("환경 변수 ID"),
                fieldWithPath("data.deployments[].envs[].key").type(JsonFieldType.STRING)
                        .description("환경 변수 키"),
                fieldWithPath("data.deployments[].envs[].value").type(JsonFieldType.STRING)
                        .description("환경 변수 값"),

                fieldWithPath("data.deployments[].hostingResponses").type(JsonFieldType.ARRAY)
                        .description("호스팅 응답 목록"),
                fieldWithPath("data.deployments[].hostingResponses[].hostingId").type(JsonFieldType.NUMBER)
                        .description("호스팅 ID"),
                fieldWithPath("data.deployments[].hostingResponses[].hostingPort").type(JsonFieldType.NUMBER)
                        .description("호스팅 포트"),
                fieldWithPath("data.deployments[].hostingResponses[].serviceType").type(JsonFieldType.STRING)
                        .description("호스팅 서비스 타입"),
                fieldWithPath("data.deployments[].hostingResponses[].detailDomainName").type(JsonFieldType.STRING)
                        .description("호스팅 서브 도메인 이름")
        };
    }

    public static FieldDescriptor[] getProjectPages() {
        return new FieldDescriptor[] {
                fieldWithPath("data.pageNumber").type(JsonFieldType.NUMBER).description("현재 페이지 번호"), // 추가
                fieldWithPath("data.pageSize").type(JsonFieldType.NUMBER).description("페이지 크기"), // 추가
                fieldWithPath("data.totalElements").type(JsonFieldType.NUMBER).description("전체 항목 수"), // Changed from `total`
                fieldWithPath("data.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수"), // Changed from `totalPages`
                fieldWithPath("data.content").type(JsonFieldType.ARRAY).description("프로젝트 목록"),
                fieldWithPath("data.content[].id").type(JsonFieldType.NUMBER).description("프로젝트 ID"),
                fieldWithPath("data.content[].userId").type(JsonFieldType.NUMBER).description("사용자 ID"), // Changed from `userid`
                fieldWithPath("data.content[].projectName").type(JsonFieldType.STRING).description("프로젝트 이름"),
                fieldWithPath("data.content[].domainName").type(JsonFieldType.STRING).description("도메인 이름"),
                fieldWithPath("data.content[].createdAt").type(JsonFieldType.ARRAY).description("생성 일시"),
                fieldWithPath("data.content[].updatedAt").type(JsonFieldType.ARRAY).description("수정 일시"),
                fieldWithPath("data.content[].deployments").type(JsonFieldType.ARRAY).description("배포 정보")

        };
    }

    public static FieldDescriptor[] combineFields(FieldDescriptor[]... fieldArrays) {
        List<FieldDescriptor> allFields = new ArrayList<>();
        for (FieldDescriptor[] fieldArray : fieldArrays) {
            allFields.addAll(Arrays.asList(fieldArray));
        }
        return allFields.toArray(new FieldDescriptor[0]);
    }
}
