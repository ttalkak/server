package com.ttalkak.project.project.application.inputport;

import com.ttalkak.project.common.UseCase;
import com.ttalkak.project.global.error.ErrorCode;
import com.ttalkak.project.global.exception.BusinessException;
import com.ttalkak.project.project.application.outputport.LoadProjectOutputPort;
import com.ttalkak.project.project.application.outputport.SaveProjectOutputPort;
import com.ttalkak.project.project.application.usercase.CreateProjectUseCase;
import com.ttalkak.project.project.application.usercase.GetProjectUseCase;
import com.ttalkak.project.project.domain.model.ProjectEntity;
import com.ttalkak.project.project.framework.web.request.ProjectCreateRequest;
import com.ttalkak.project.project.framework.web.response.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CreateProjectInputPort implements CreateProjectUseCase {

    private final SaveProjectOutputPort saveProjectOutputPort;

    private final LoadProjectOutputPort loadProjectOutputPort;

    /**
     * 프로젝트 생성
     * @param projectCreateRequest
     * @return
     */
    @Transactional
    @Override
    public ProjectResponse createProject(ProjectCreateRequest projectCreateRequest) {

        // 도메인명 중복 체크
        if(projectCreateRequest.getDomainName() != null && !"".equals(projectCreateRequest.getDomainName())) {
            ProjectEntity projectEntity = loadProjectOutputPort.findByDomainName(projectCreateRequest.getDomainName());
            if(projectEntity != null) {
                throw new BusinessException(ErrorCode.ALREADY_EXISTS_DOMAIN_NAME);
            }
        }

        ProjectEntity projectEntity = ProjectEntity.builder()
                .projectName(projectCreateRequest.getProjectName())
                .domainName(projectCreateRequest.getDomainName())
                .userId(projectCreateRequest.getUserId())
                .build();

        ProjectEntity result = saveProjectOutputPort.save(projectEntity);



        return ProjectResponse.mapToResponse(result);
    }
}
