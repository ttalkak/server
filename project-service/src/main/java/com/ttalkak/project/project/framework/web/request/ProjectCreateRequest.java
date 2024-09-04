package com.ttalkak.project.project.framework.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProjectCreateRequest {

    private Long userId;

    private String projectName;

    private String domainName;
}
