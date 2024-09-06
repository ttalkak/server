package com.ttalkak.project.project.application.usercase;

import com.ttalkak.project.project.framework.web.request.DomainNameRequest;
import com.ttalkak.project.project.framework.web.response.ProjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetProjectUseCase {

    ProjectResponse getProject(Long projectId);

    ProjectResponse getFeignProject(Long projectId);

    Page<ProjectResponse> getProjects(Pageable pageable, String searchKeyword, Long userId);

    Boolean isDuplicateDomainName(DomainNameRequest domainNameRequest);
}
